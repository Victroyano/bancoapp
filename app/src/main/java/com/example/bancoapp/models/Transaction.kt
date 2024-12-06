package com.example.bancoapp.com.example.bancoapp.models
data class Transaction(
    val id_transaccion: Int,
    val id_operacion: Int,
    val id_cuenta_origen: Int,
    val id_cuenta_destino: Int,
    val monto: Double,
    val fecha_transaccion: String,
    val descripcion: String?
)
