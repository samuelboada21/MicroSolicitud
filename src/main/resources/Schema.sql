CREATE TABLE IF NOT EXISTS estado (
    id_estado INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tipo_prestamo (
    id_tipo_prestamo INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    monto_minimo DECIMAL(19, 2) NOT NULL,
    monto_maximo DECIMAL(19, 2) NOT NULL,
    tasa_interes DECIMAL(5, 2) NOT NULL,
    validacion_automatica BOOLEAN NOT NULL
);


CREATE TABLE IF NOT EXISTS solicitud (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(19, 2) NOT NULL,
    plazo INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    id_estado INT NOT NULL,
    id_tipo_prestamo INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_estado) REFERENCES estado(id_estado),
    FOREIGN KEY (id_tipo_prestamo) REFERENCES tipo_prestamo(id_tipo_prestamo)
);

