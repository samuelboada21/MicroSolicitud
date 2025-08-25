package com.solicitud.solicitud.infrastructure.repository;

import com.solicitud.solicitud.domain.model.Estado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface EstadoRepository extends ReactiveCrudRepository<Estado, Integer> {
    Mono<Estado> findByNombre(String nombre);
}