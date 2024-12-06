package com.example.bancoapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.bancoapp.com.example.bancoapp.ApiClient
import com.example.bancoapp.com.example.bancoapp.MainActivity
import com.example.bancoapp.com.example.bancoapp.SessionManager
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        // Si ya hay sesión, ir a MainActivity directamente
        if (sessionManager.isLoggedIn()) {
            navigateToMain()
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val url = "http://18.223.99.187/api/login.php"
                val postData = "username=$username&password=$password"
                ApiClient.postRequest(url, postData) { response, error ->
                    runOnUiThread {
                        if (error != null) {
                            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                        } else {
                            try {
                                val jsonResponse = JSONObject(response)
                                val success = jsonResponse.getBoolean("success")

                                if (success) {
                                    val token = jsonResponse.getString("token")
                                    val userId = jsonResponse.getInt("id_usuario")

                                    // Guardar los datos reales del login
                                    sessionManager.saveAuthToken(token)
                                    sessionManager.saveUserId(userId)

                                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                                    navigateToMain()
                                } else {
                                    val message = jsonResponse.getString("message")
                                    Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this,
                                    "Error al procesar la respuesta del servidor",
                                    Toast.LENGTH_SHORT
                                ).show()
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}