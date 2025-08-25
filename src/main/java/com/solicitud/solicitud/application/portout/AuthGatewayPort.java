package com.solicitud.solicitud.application.portout;

import reactor.core.publisher.Mono;

public interface AuthGatewayPort {
    Mono<Boolean> verificarUsuarioExiste(String documentoIdentidad);
}
