package com.example.bancoapp.com.example.bancoapp.models
data class User(
    val id_usuario: Int,
    val username: String,
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val fecha_nac: String,
    val numero_cuenta: String,
    val saldo: Double,
    val cve: Int
)
