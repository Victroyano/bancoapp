package com.example.bancoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.com.example.bancoapp.ApiClient

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etFechaNac = findViewById<EditText>(R.id.etFechaNac)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etPregunta = findViewById<EditText>(R.id.etPregunta)
        val etRespuesta = findViewById<EditText>(R.id.etRespuesta)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val fechaNac = etFechaNac.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val pregunta = etPregunta.text.toString().trim()
            val respuesta = etRespuesta.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || fechaNac.isEmpty() ||
                email.isEmpty() || telefono.isEmpty() || username.isEmpty() ||
                password.isEmpty() || pregunta.isEmpty() || respuesta.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamada a la API de registro (ejemplo)
            val url = "http://18.223.99.187/api/register.php"
            val postData = "nombre=$nombre&apellido=$apellido&fecha_nac=$fechaNac&email=$email&telefono=$telefono&username=$username&password=$password&pregunta=$pregunta&respuesta=$respuesta"
            ApiClient.postRequest(url, postData) { response, error ->
                runOnUiThread {
                    if (error != null) {
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}
