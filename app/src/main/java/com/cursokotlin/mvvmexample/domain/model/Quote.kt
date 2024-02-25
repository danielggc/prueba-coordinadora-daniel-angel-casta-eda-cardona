package com.cursokotlin.mvvmexample.domain.model

import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity
import com.cursokotlin.mvvmexample.data.model.RemissionModel

data class Quote (val quote:String, val author:String)
data class Remission(
    val order:Int,
    val id: String ,
    val codigoRemision :String,
    val direccionDestinatario:String,
    val nombreTerminalDestino:String,
    val telefonoDestinatario :String,
    val nombreDestinatario:String,
    val oriogen:String,
    val firstOrder:Int,
    var StatusExpandable:Boolean  = false
)


fun RemissionModel.toDomain( idex :Int ): Remission {
    return Remission(
        order = idex ,
        id,
        codigo_remision,
        destinatario.direccion,
        nombre_terminal_destino,
        destinatario.telefono
    )
}


fun RemissionEntity.toDomain(): Remission {
    return Remission(
        order,
        id = id,
        codigoRemision = codigoRemision,
        direccionDestinatario = direccionDestinatario,
        nombreTerminalDestino = nombreTerminalDestino,
        telefonoDestinatario = telefonoDestinatario
    )
}




