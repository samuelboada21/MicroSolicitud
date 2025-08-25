package com.solicitud.solicitud.domain.usecase;

import com.solicitud.solicitud.application.portin.SolicitarPrestamoPort;
import com.solicitud.solicitud.application.portout.*;
import com.solicitud.solicitud.domain.dto.SolicitudDto;
import com.solicitud.solicitud.domain.model.Solicitud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class SolicitarPrestamoUseCase implements SolicitarPrestamoPort {

    private static final Logger log = LoggerFactory.getLogger(SolicitarPrestamoUseCase.class);

    private final SolicitudRepositoryPort solicitudRepositoryPort;
    private final TipoPrestamoRepositoryPort tipoPrestamoRepositoryPort;
    private final EstadoRepositoryPort estadoRepositoryPort;
    private final AuthGatewayPort authGatewayPort;

    public SolicitarPrestamoUseCase(SolicitudRepositoryPort solicitudRepositoryPort, TipoPrestamoRepositoryPort tipoPrestamoRepositoryPort, EstadoRepositoryPort estadoRepositoryPort, AuthGatewayPort authGatewayPort) {
        this.solicitudRepositoryPort = solicitudRepositoryPort;
        this.tipoPrestamoRepositoryPort = tipoPrestamoRepositoryPort;
        this.estadoRepositoryPort = estadoRepositoryPort;
        this.authGatewayPort = authGatewayPort;
    }

    @Override
    public Mono<Solicitud> solicitar(SolicitudDto solicitudDto) {
        log.info("Iniciando la solicitud de préstamo del usuario con email: {}", solicitudDto.getEmail());

        return validarUsuario(solicitudDto.getDocumentoIdentidad())
                .then(validarTipoPrestamo(solicitudDto))
                .flatMap(this::crearSolicitud)
                .doOnSuccess(solicitud -> log.info("Solicitud guardada exitosamente con ID: {}", solicitud.getIdSolicitud()))
                .doOnError(throwable -> log.error("Error al procesar la solicitud: {}", throwable.getMessage()));
    }

    private Mono<Void> validarUsuario(String documentoIdentidad) {
        log.info("Verificando la existencia del usuario con documento de identidad: {}", documentoIdentidad);
        return authGatewayPort.verificarUsuarioExiste(documentoIdentidad)
                .flatMap(existe -> {
                    if (!existe) {
                        return Mono.error(new IllegalArgumentException("El usuario no existe en el sistema."));
                    }
                    return Mono.empty();
                });
    }

    private Mono<Solicitud> validarTipoPrestamo(SolicitudDto solicitudDto) {
        log.info("Validando el tipo de préstamo.");
        return tipoPrestamoRepositoryPort.findById(solicitudDto.getIdTipoPrestamo())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El tipo de préstamo no existe.")))
                .flatMap(tipoPrestamo -> {
                    if (solicitudDto.getMonto().compareTo(tipoPrestamo.getMontoMinimo()) < 0 ||
                            solicitudDto.getMonto().compareTo(tipoPrestamo.getMontoMaximo()) > 0) {
                        return Mono.error(new IllegalArgumentException("El monto solicitado no está dentro del rango permitido para este tipo de préstamo."));
                    }
                    Solicitud solicitud = new Solicitud();
                    solicitud.setMonto(solicitudDto.getMonto());
                    solicitud.setPlazo(solicitudDto.getPlazo());
                    solicitud.setEmail(solicitudDto.getEmail());
                    solicitud.setIdTipoPrestamo(tipoPrestamo.getIdTipoPrestamo());
                    return Mono.just(solicitud);
                });
    }

    private Mono<Solicitud> crearSolicitud(Solicitud solicitud) {
        log.info("Paso 3: Creando y guardando la solicitud.");
        return estadoRepositoryPort.findByNombre("Pendiente de revisión")
                .switchIfEmpty(Mono.error(new IllegalStateException("El estado inicial 'Pendiente de revisión' no se encontró.")))
                .flatMap(estado -> {
                    solicitud.setIdEstado(estado.getIdEstado());
                    solicitud.setFechaCreacion(LocalDateTime.now());
                    return solicitudRepositoryPort.save(solicitud);
                });
    }
}
