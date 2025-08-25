package com.solicitud.solicitud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("solicitud")
public class Solicitud {

    @Id
    private Integer idSolicitud;

    private BigDecimal monto;
    private Integer plazo;
    private String email;
    private Integer idEstado;
    private Integer idTipoPrestamo;
    private LocalDateTime fechaCreacion;

}
