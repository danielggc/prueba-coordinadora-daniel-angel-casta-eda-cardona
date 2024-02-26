package com.shipping.test_cordinadora.data.model


data class ApiResponse(
    val isError: Boolean,
    val data: List<RemissionModel>
)

data class RemissionModel(
    val abreviado_producto: String,
    val codigo_remision: String,
    val tipo: String,
    val codigo_terminal_origen: Int,
    val nombre_producto: String,
    val remitente: Sender,
    val codigo_terminal: Int,
    val alerta_nys: Boolean,
    val nivel_servicio: String,
    val longitud: String,
    val abreviado_nivel_servicio: String,
    val abreviado_cuenta: String,
    val fecha_hora_registro: DateTime,
    val codigo_tipo_cuenta: Int,
    val orden: Int,
    val identificacion_externo: String,
    val nombre_terminal_origen: String,
    val latitud: String,
    val total_unidades: Int,
    val nombre_equipo: String,
    val codigo_equipo: Int,
    val codigo_nivel_servicio: Int,
    val codigo_seguridad: String,
    val destinatario: Recipient,
    val codigo_ciudad_destino: String,
    val fecha: String,
    val tiene_pago: Boolean,
    val observaciones: String,
    val unidades_asignadas: List<String>,
    val codigo_producto: Int,
    val codigo_terminal_destino: Int,
    val nombre_terminal_destino: String,
    val activo: Boolean,
    val id: String
)

data class Sender(
    val ciudad: String,
    val nit: String,
    val direccion: String,
    val telefono: String,
    val nombre: String
)

data class Recipient(
    val ciudad: String,
    val nit: String,
    val direccion: String,
    val telefono: String,
    val nombre: String
)

data class DateTime(
    val _seconds: Long,
    val _nanoseconds: Int
)
























