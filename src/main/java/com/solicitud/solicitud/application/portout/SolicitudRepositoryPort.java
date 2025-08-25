package com.solicitud.solicitud.application.portout;

import com.solicitud.solicitud.domain.model.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitudRepositoryPort {
    Mono<Solicitud> save(Solicitud solicitud);
}
