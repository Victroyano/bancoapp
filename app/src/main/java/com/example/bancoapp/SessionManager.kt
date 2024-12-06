package com.example.bancoapp.com.example.bancoapp
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "id_usuario"
    }

    fun saveAuthToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun saveUserId(userId: Int) {
        prefs.edit().putInt(USER_ID_KEY, userId).apply()
    }

    fun getUserId(): Int {
        return prefs.getInt(USER_ID_KEY, -1)
    }

    fun isLoggedIn(): Boolean {
        return getAuthToken() != null && getUserId() != -1
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
