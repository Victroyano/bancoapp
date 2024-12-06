package com.example.bancoapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etRespuesta = findViewById<EditText>(R.id.etRespuesta)
        val etNuevaContrasena = findViewById<EditText>(R.id.etNuevaContrasena)
        val btnResetPassword = findViewById<Button>(R.id.btnResetPassword)

        btnResetPassword.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val respuesta = etRespuesta.text.toString().trim()
            val nuevaContrasena = etNuevaContrasena.text.toString().trim()

            if (username.isEmpty() || respuesta.isEmpty() || nuevaContrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamada a la API de reset password (ejemplo)
            val url = "http://18.223.99.187/api/reset_password.php"
            val postData = "username=$username&respuesta=$respuesta&nueva_contraseña=$nuevaContrasena"
            ApiClient.postRequest(url, postData) { response, error ->
                runOnUiThread {
                    if (error != null) {
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Contraseña restablecida exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}
