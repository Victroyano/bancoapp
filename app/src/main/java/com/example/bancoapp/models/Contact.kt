package com.example.bancoapp.com.example.bancoapp.models
data class Contact(
    val id_contacto: Int,
    val nombre_contacto: String,
    val numero_cuenta: String,
    val alias: String? = null
)
