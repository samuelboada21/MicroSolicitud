INSERT IGNORE INTO estado (id_estado, nombre, descripcion) VALUES
(1, 'Pendiente de revisión', 'La solicitud ha sido recibida y está en espera de ser evaluada.'),
(2, 'Aprobado', 'La solicitud ha sido aprobada y el préstamo puede ser desembolsado.'),
(3, 'Rechazado', 'La solicitud ha sido rechazada y no se otorgará el préstamo.');

INSERT IGNORE INTO tipo_prestamo (id_tipo_prestamo, nombre, monto_minimo, monto_maximo, tasa_interes, validacion_automatica) VALUES
(1, 'Préstamo Express', 500000.00, 2000000.00, 0.15, 0),
(2, 'Microcrédito', 100000.00, 5000000.00, 0.10, 1),
(3, 'Préstamo de Vehículo', 10000000.00, 50000000.00, 0.08, 1),
(4, 'Préstamo Hipotecario', 80000000.00, 250000000.00, 0.05, 1);