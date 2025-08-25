package com.solicitud.solicitud.infrastructure.adapter.input;

import com.solicitud.solicitud.application.portin.SolicitarPrestamoPort;
import com.solicitud.solicitud.domain.dto.SolicitudDto;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public class SolicitudHandler {

    private static final Logger log = LoggerFactory.getLogger(SolicitudHandler.class);
    private final SolicitarPrestamoPort solicitarPrestamoPort;
    private final Validator validator;

    public SolicitudHandler(SolicitarPrestamoPort solicitarPrestamoPort, Validator validator) {
        this.solicitarPrestamoPort = solicitarPrestamoPort;
        this.validator = validator;
    }

    public Mono<ServerResponse> solicitarPrestamo(ServerRequest request) {
        log.info("Solicitud de préstamo recibida en el HANDLER.");
        return request.bodyToMono(SolicitudDto.class)
                .doOnNext(this::validateRequest)
                .flatMap(solicitarPrestamoPort::solicitar)
                .flatMap(solicitud -> {
                    log.info("Solicitud procesada exitosamente, devolviendo respuesta 201 Created.");
                    return ServerResponse.status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(solicitud);
                })
                .onErrorResume(IllegalArgumentException.class, e -> {
                    log.error("Error de validación: {}", e.getMessage());
                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                });
    }

    private void validateRequest(SolicitudDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validación: " + violations.iterator().next().getMessage());
        }
    }
}
