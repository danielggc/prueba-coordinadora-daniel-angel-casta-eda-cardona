package com.shipping.test_cordinadora.domain.model

import com.shipping.test_cordinadora.data.database.entities.RemissionEntity
import com.shipping.test_cordinadora.data.model.RemissionModel


data class Remission(
    val order:Int,
    val firstOrder:Int,
    val id: String ,
    val codigoRemision :String,
    val direccionDestinatario:String,
    val nombreTerminalDestino:String,
    val telefonoDestinatario :String,
    val nombreDestinatario:String,
    val oriogen:String,
    val latitud :String,
    val longitud:String,
    var StatusExpandable:Boolean  = false,


)


fun RemissionModel.toDomain( index :Int ): Remission {
    return Remission(
        order = index ,
        firstOrder = index,
        id,
        codigo_remision,
        destinatario.direccion,
        nombre_terminal_destino,
        destinatario.telefono,
        destinatario.nombre,
        remitente.ciudad,
        latitud ,
        longitud,
    )
}


fun RemissionEntity.toDomain(): Remission {
    return Remission(
        order                 = order,
        id                    = id,
        codigoRemision        = codigoRemision,
        direccionDestinatario = direccionDestinatario,
        nombreTerminalDestino = nombreTerminalDestino,
        telefonoDestinatario  = telefonoDestinatario,
        nombreDestinatario    = nombreDestinatario,
        oriogen               = oriogen,
        firstOrder            = firstOrder ,
        latitud               = latitud ,
        longitud              = longitud,
    )
}




