package com.solicitud.solicitud.application.portin;

import com.solicitud.solicitud.domain.dto.SolicitudDto;
import com.solicitud.solicitud.domain.model.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitarPrestamoPort {
    Mono<Solicitud> solicitar(SolicitudDto solicitudDto);
}