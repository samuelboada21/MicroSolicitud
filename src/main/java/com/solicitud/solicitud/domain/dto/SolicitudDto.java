package com.solicitud.solicitud.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SolicitudDto {

    @NotBlank(message = "El documento de identidad no puede estar vacío.")
    private String documentoIdentidad;

    @NotBlank(message = "El email no puede estar vacío.")
    @Email(message = "El formato del email no es válido.")
    private String email;

    @NotNull(message = "El monto no puede ser nulo.")
    @DecimalMin(value = "0.00", inclusive = false, message = "El monto debe ser mayor que cero.")
    private BigDecimal monto;

    @NotNull(message = "El plazo no puede ser nulo.")
    @Min(value = 1, message = "El plazo debe ser al menos 1.")
    private Integer plazo;

    @NotNull(message = "El ID del tipo de préstamo no puede ser nulo.")
    private Integer idTipoPrestamo;
}
