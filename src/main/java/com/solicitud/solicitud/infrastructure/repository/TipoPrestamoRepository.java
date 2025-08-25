package com.solicitud.solicitud.infrastructure.repository;

import com.solicitud.solicitud.domain.model.TipoPrestamo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepository extends ReactiveCrudRepository<TipoPrestamo, Integer> {
}
