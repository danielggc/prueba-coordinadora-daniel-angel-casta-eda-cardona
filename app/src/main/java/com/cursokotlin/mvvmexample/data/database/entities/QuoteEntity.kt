package com.cursokotlin.mvvmexample.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cursokotlin.mvvmexample.domain.model.Remission


@Entity(tableName = "remission_table")
data class RemissionEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String ,
    @ColumnInfo(name = "codigo_remision") val codigoRemision: String,
    @ColumnInfo(name = "direccion_destinatario") val direccionDestinatario: String,
    @ColumnInfo(name = "nombre_terminal_destino") val nombreTerminalDestino: String,
    @ColumnInfo(name = "telefono_destinatario") val telefonoDestinatario: String
)


fun Remission.toDatabase(): RemissionEntity {
    return RemissionEntity(
        id = id, // Si id es de tipo String y necesitas convertirlo a Int
        codigoRemision = codigoRemision,
        direccionDestinatario = direccionDestinatario,
        nombreTerminalDestino = nombreTerminalDestino,
        telefonoDestinatario = telefonoDestinatario
    )
}