package com.solicitud.solicitud.infrastructure.adapter.output;

import com.solicitud.solicitud.application.portout.EstadoRepositoryPort;
import com.solicitud.solicitud.domain.model.Estado;
import com.solicitud.solicitud.infrastructure.repository.EstadoRepository;
import reactor.core.publisher.Mono;

public class EstadoRepositoryAdapter implements EstadoRepositoryPort {

    private final EstadoRepository estadoRepository;

    public EstadoRepositoryAdapter(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Mono<Estado> findByNombre(String nombre) {
        return estadoRepository.findByNombre(nombre);
    }
}