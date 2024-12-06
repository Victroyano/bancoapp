package com.example.bancoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.com.example.bancoapp.SessionManager

class ProfileActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sessionManager = SessionManager(this)

        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvApellido = findViewById<TextView>(R.id.tvApellido)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val tvFechaNac = findViewById<TextView>(R.id.tvFechaNac)
        val tvNumeroCuenta = findViewById<TextView>(R.id.tvNumeroCuenta)
        val tvSaldo = findViewById<TextView>(R.id.tvSaldo)
        val tvCve = findViewById<TextView>(R.id.tvCve)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Aquí podrías hacer una llamada a la API `get_profile.php` y actualizar estos campos
        // Por ahora, valores estáticos de ejemplo:
        tvUsername.text = "Username: usuario123"
        tvNombre.text = "Nombre: Juan"
        tvApellido.text = "Apellido: Pérez"
        tvEmail.text = "Email: juan.perez@banco.com"
        tvTelefono.text = "Teléfono: +1234567890"
        tvFechaNac.text = "Fecha de Nacimiento: 1990-01-01"
        tvNumeroCuenta.text = "Número de Cuenta: 12345678"
        tvSaldo.text = "Saldo: $1000.00"
        tvCve.text = "Clave: 123"

        btnCerrarSesion.setOnClickListener {
            sessionManager.clearSession()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
