package com.example.bancoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.com.example.bancoapp.ApiClient

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val etNombreContacto = findViewById<EditText>(R.id.etNombreContacto)
        val etNumeroCuenta = findViewById<EditText>(R.id.etNumeroCuenta)
        val etAlias = findViewById<EditText>(R.id.etAlias)
        val btnAgregarContacto = findViewById<Button>(R.id.btnAgregarContacto)

        btnAgregarContacto.setOnClickListener {
            val nombreContacto = etNombreContacto.text.toString().trim()
            val numeroCuenta = etNumeroCuenta.text.toString().trim()
            val alias = etAlias.text.toString().trim()

            if (nombreContacto.isEmpty() || numeroCuenta.isEmpty() || alias.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamada a la API para agregar contacto (ejemplo)
            val url = "http://18.223.99.187/api/add_contact.php"
            val postData = "id_usuario=1&nombre_contacto=$nombreContacto&numero_cuenta=$numeroCuenta&alias=$alias"
            ApiClient.postRequest(url, postData) { response, error ->
                runOnUiThread {
                    if (error != null) {
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Contacto agregado exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}
