package com.cursokotlin.mvvmexample.domain.model

import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity
import com.cursokotlin.mvvmexample.data.model.RemissionModel

data class Quote (val quote:String, val author:String)
data class Remission(    val id: String , val codigoRemision :String, val direccionDestinatario:String, val nombreTerminalDestino:String, val telefonoDestinatario :String  ,var StatusExpandable:Boolean  = false)


fun RemissionModel.toDomain(): Remission {
    return Remission(
        id,
        codigo_remision,
        destinatario.direccion,
        nombre_terminal_destino,
        destinatario.telefono
    )
}


fun RemissionEntity.toDomain(): Remission {
    return Remission(
        id = id.toString(), // Si id es de tipo Int y necesitas convertirlo a String
        codigoRemision = codigoRemision,
        direccionDestinatario = direccionDestinatario,
        nombreTerminalDestino = nombreTerminalDestino,
        telefonoDestinatario = telefonoDestinatario
    )
}




