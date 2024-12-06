package com.example.bancoapp.com.example.bancoapp
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object ApiClient {

    fun postRequest(urlString: String, postData: String, callback: (response: String?, error: String?) -> Unit) {
        Thread {
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                connection.doOutput = true

                OutputStreamWriter(connection.outputStream).use {
                    it.write(postData)
                    it.flush()
                }

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().readText()
                    callback(response, null)
                } else {
                    callback(null, "Error de servidor: $responseCode")
                }
                connection.disconnect()
            } catch (e: Exception) {
                callback(null, e.message)
                e.printStackTrace()
            }
        }.start()
    }

    fun getRequest(urlString: String, callback: (response: String?, error: String?) -> Unit) {
        Thread {
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().readText()
                    callback(response, null)
                } else {
                    callback(null, "Error de servidor: $responseCode")
                }
                connection.disconnect()
            } catch (e: Exception) {
                callback(null, e.message)
                e.printStackTrace()
            }
        }.start()
    }
}
