package com.solicitud.solicitud.infrastructure.config;

import com.solicitud.solicitud.application.portin.SolicitarPrestamoPort;
import com.solicitud.solicitud.application.portout.*;
import com.solicitud.solicitud.domain.usecase.SolicitarPrestamoUseCase;
import com.solicitud.solicitud.infrastructure.adapter.input.SolicitudHandler;
import com.solicitud.solicitud.infrastructure.adapter.output.*;
import com.solicitud.solicitud.infrastructure.repository.EstadoRepository;
import com.solicitud.solicitud.infrastructure.repository.SolicitudRepository;
import com.solicitud.solicitud.infrastructure.repository.TipoPrestamoRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public SolicitarPrestamoPort solicitarPrestamoPort(SolicitudRepositoryPort solicitudRepositoryPort, TipoPrestamoRepositoryPort tipoPrestamoRepositoryPort, EstadoRepositoryPort estadoRepositoryPort, AuthGatewayPort authGatewayPort) {
        return new SolicitarPrestamoUseCase(solicitudRepositoryPort, tipoPrestamoRepositoryPort, estadoRepositoryPort, authGatewayPort);
    }

    @Bean
    public SolicitudRepositoryPort solicitudRepositoryPort(SolicitudRepository solicitudRepository) {
        return new SolicitudRepositoryAdapter(solicitudRepository);
    }

    @Bean
    public TipoPrestamoRepositoryPort tipoPrestamoRepositoryPort(TipoPrestamoRepository tipoPrestamoRepository) {
        return new TipoPrestamoRepositoryAdapter(tipoPrestamoRepository);
    }

    @Bean
    public EstadoRepositoryPort estadoRepositoryPort(EstadoRepository estadoRepository) {
        return new EstadoRepositoryAdapter(estadoRepository);
    }

    @Bean
    public AuthGatewayPort authGatewayPort(@Value("${auth.service.url}") String authServiceUrl) {
        return new AuthGatewayAdapter(authServiceUrl);
    }

    @Bean
    public SolicitudHandler solicitudHandler(SolicitarPrestamoPort solicitarPrestamoPort, Validator validator) {
        return new SolicitudHandler(solicitarPrestamoPort, validator);
    }
}