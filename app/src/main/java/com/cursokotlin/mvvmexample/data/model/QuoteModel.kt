package com.cursokotlin.mvvmexample.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)
class DeliveryInfo(
    val codigoRemision: String,
    val codigoTerminalOrigen: Int,
    val telefonoDestinatario: String,
    val direccionDestinatario: String,
    val ubicacion: Ubicacion?,
    val estado: Estado,
    var expandable : Boolean =false
) {
    data class Remitente(
        val ciudad: String,
        val nit: String,
        val direccion: String,
        val telefono: String,
        val nombre: String
    )

    data class Destinatario(
        val ciudad: String,
        val nit: String,
        val direccion: String,
        val telefono: String,
        val nombre: String
    )

    data class FechaHoraRegistro(
        val seconds: Long,
        val nanoseconds: Long
    )

    data class Ubicacion(
        val longitud: String,
        val latitud: String
    )

    data class Estado(
        val nivelServicio: String,
        val abreviadoNivelServicio: String,
        val abreviadoCuenta: String,
        val codigoTipoCuenta: Int,
        val orden: Int,
        val codigoNivelServicio: Int,
        val codigoSeguridad: String,
        val tienePago: Boolean,
        val observaciones: String,
        val unidadesAsignadas: List<String>,
        val codigoProducto: Int,
        val codigoTerminalDestino: Int,
        val nombreTerminalDestino: String,
        val activo: Boolean,
        val id: String
    )
}