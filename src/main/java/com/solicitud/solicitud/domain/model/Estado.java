package com.solicitud.solicitud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("estado")
public class Estado {

    @Id
    private Integer idEstado;
    private String nombre;
    private String descripcion;
}
