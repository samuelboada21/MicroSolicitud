package com.solicitud.solicitud.infrastructure.repository;

import com.solicitud.solicitud.domain.model.Solicitud;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SolicitudRepository extends ReactiveCrudRepository<Solicitud, Integer> {
}
