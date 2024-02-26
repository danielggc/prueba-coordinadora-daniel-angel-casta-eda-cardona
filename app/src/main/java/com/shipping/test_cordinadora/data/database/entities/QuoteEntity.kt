package com.shipping.test_cordinadora.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shipping.test_cordinadora.domain.model.Remission


@Entity(tableName = "remission_table")
data class RemissionEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String ,
    @ColumnInfo(name = "codigo_remision") val codigoRemision: String,
    @ColumnInfo(name = "direccion_destinatario") val direccionDestinatario: String,
    @ColumnInfo(name = "nombre_terminal_destino") val nombreTerminalDestino: String,
    @ColumnInfo(name = "telefono_destinatario") val telefonoDestinatario: String,
    @ColumnInfo(name = "nombre_destinatario") val nombreDestinatario: String,
    @ColumnInfo(name = "oriogen") val oriogen: String,
    @ColumnInfo(name = "order" ) val order:Int,
    @ColumnInfo(name = "first_Order" ) val firstOrder:Int,
    @ColumnInfo(name = "latitud" ) val latitud:String,
    @ColumnInfo(name = "longitud" ) val longitud:String,

    )


fun Remission.toDatabase(): RemissionEntity {
    return RemissionEntity(
        order                       = order,
        id                          = id,
        codigoRemision              = codigoRemision,
        direccionDestinatario       = direccionDestinatario,
        nombreTerminalDestino       = nombreTerminalDestino,
        telefonoDestinatario        = telefonoDestinatario,
        nombreDestinatario          = nombreDestinatario,
        oriogen                     = oriogen,
        latitud                     = latitud ,
        longitud                    = longitud,
        firstOrder                  = firstOrder


    )
}