package com.solicitud.solicitud.application.portout;

import com.solicitud.solicitud.domain.model.TipoPrestamo;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepositoryPort {
    Mono<TipoPrestamo> findById(Integer id);
}
