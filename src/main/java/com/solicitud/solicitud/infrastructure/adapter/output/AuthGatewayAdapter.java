package com.solicitud.solicitud.infrastructure.adapter.output;

import com.solicitud.solicitud.application.portout.AuthGatewayPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class AuthGatewayAdapter implements AuthGatewayPort {

    private final WebClient webClient;

    public AuthGatewayAdapter(String authServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(authServiceUrl).build();
    }

    @Override
    public Mono<Boolean> verificarUsuarioExiste(String documentoIdentidad) {
        return webClient.get()
                .uri("/api/v1/usuarios/documento/{documento}", documentoIdentidad)
                .retrieve()
                .bodyToMono(Void.class)
                .thenReturn(true)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.empty();
                    }
                    return Mono.error(new RuntimeException("Error del servicio de autenticación: " + ex.getResponseBodyAsString()));
                })
                .defaultIfEmpty(false);
    }
}