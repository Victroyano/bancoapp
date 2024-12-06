package com.example.bancoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapp.com.example.bancoapp.ApiClient

class TransferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        val etNumeroCuentaDestino = findViewById<EditText>(R.id.etNumeroCuentaDestino)
        val etMonto = findViewById<EditText>(R.id.etMonto)
        val btnTransferir = findViewById<Button>(R.id.btnTransferir)
        val btnAgregarContacto = findViewById<Button>(R.id.btnAgregarContacto)

        btnTransferir.setOnClickListener {
            val cuentaDestino = etNumeroCuentaDestino.text.toString().trim()
            val montoStr = etMonto.text.toString().trim()

            if (cuentaDestino.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = montoStr.toDoubleOrNull()
            if (monto == null || monto <= 0) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamada a la API de transferencia (ejemplo)
            val url = "http://18.223.99.187/api/transfer.php"
            val postData = "id_cuenta_origen=1&id_cuenta_destino=2&monto=$monto"
            ApiClient.postRequest(url, postData) { response, error ->
                runOnUiThread {
                    if (error != null) {
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Transferencia realizada exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }

        btnAgregarContacto.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}
