package com.example.bancoapp.com.example.bancoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.AddContactActivity
import com.example.bancoapp.LoginActivity
import com.example.bancoapp.ProfileActivity
import com.example.bancoapp.R
import com.example.bancoapp.TransferActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        // Si no está logueado, vuelve al Login
        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val tvNumeroCuenta = findViewById<TextView>(R.id.tvNumeroCuenta)
        val tvSaldo = findViewById<TextView>(R.id.tvSaldo)
        val btnTransfer = findViewById<Button>(R.id.btnTransfer)
        val btnAddContact = findViewById<Button>(R.id.btnAddContact)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Aquí podrías cargar datos reales del perfil (ejemplo simple)
        tvNumeroCuenta.text = "Cuenta: 12345678"
        tvSaldo.text = "Saldo: $1000.00"

        btnTransfer.setOnClickListener {
            startActivity(Intent(this, TransferActivity::class.java))
        }

        btnAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            sessionManager.clearSession()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
