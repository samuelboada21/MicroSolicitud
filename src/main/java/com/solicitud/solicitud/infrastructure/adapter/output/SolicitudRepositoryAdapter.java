package com.solicitud.solicitud.infrastructure.adapter.output;

import com.solicitud.solicitud.application.portout.SolicitudRepositoryPort;
import com.solicitud.solicitud.domain.model.Solicitud;
import com.solicitud.solicitud.infrastructure.repository.SolicitudRepository;
import reactor.core.publisher.Mono;

public class SolicitudRepositoryAdapter implements SolicitudRepositoryPort {

    private final SolicitudRepository solicitudRepository;

    public SolicitudRepositoryAdapter(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Mono<Solicitud> save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }
}
