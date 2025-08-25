package com.solicitud.solicitud.infrastructure.config;

import com.solicitud.solicitud.infrastructure.adapter.input.SolicitudHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Tag(name = "Solicitudes", description = "Endpoints para la gestión de solicitudes de préstamo")
public class RouterConfig {

    private final SolicitudHandler solicitudHandler;

    public RouterConfig(SolicitudHandler solicitudHandler) {
        this.solicitudHandler = solicitudHandler;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(path = "/api/v1/solicitudes", method = RequestMethod.POST,
                    operation = @Operation(
                            summary = "Crear una nueva solicitud de préstamo",
                            description = "Registra una nueva solicitud de préstamo para un usuario existente.",
                            requestBody = @RequestBody(
                                    required = true,
                                    description = "Datos de la solicitud a registrar.",
                                    content = @Content(schema = @Schema(implementation = com.solicitud.solicitud.domain.dto.SolicitudDto.class))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Solicitud creada exitosamente.",
                                            content = @Content(schema = @Schema(implementation = com.solicitud.solicitud.domain.model.Solicitud.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Error de validación de datos o usuario no encontrado."
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routes() {
        return route()
                .POST("/api/v1/solicitudes", accept(MediaType.APPLICATION_JSON), solicitudHandler::solicitarPrestamo)
                .build();
    }
}