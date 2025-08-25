package com.solicitud.solicitud.infrastructure.adapter.output;

import com.solicitud.solicitud.application.portout.TipoPrestamoRepositoryPort;
import com.solicitud.solicitud.domain.model.TipoPrestamo;
import com.solicitud.solicitud.infrastructure.repository.TipoPrestamoRepository;
import reactor.core.publisher.Mono;

public class TipoPrestamoRepositoryAdapter implements TipoPrestamoRepositoryPort {

    private final TipoPrestamoRepository tipoPrestamoRepository;

    public TipoPrestamoRepositoryAdapter(TipoPrestamoRepository tipoPrestamoRepository) {
        this.tipoPrestamoRepository = tipoPrestamoRepository;
    }

    @Override
    public Mono<TipoPrestamo> findById(Integer id) {
        return tipoPrestamoRepository.findById(id);
    }
}
