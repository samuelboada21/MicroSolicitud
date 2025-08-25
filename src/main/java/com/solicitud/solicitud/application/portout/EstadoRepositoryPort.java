package com.solicitud.solicitud.application.portout;

import com.solicitud.solicitud.domain.model.Estado;
import reactor.core.publisher.Mono;

public interface EstadoRepositoryPort {
    Mono<Estado> findByNombre(String nombre);
}
