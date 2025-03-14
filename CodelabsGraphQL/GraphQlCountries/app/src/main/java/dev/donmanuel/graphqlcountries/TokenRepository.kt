package dev.donmanuel.graphqlcountries

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * This class is responsible for managing the storage and retrieval of an authentication token.
 * It uses EncryptedSharedPreferences to securely store the token.
 */
object TokenRepository {
    /**
     * The key used to store and retrieve the token in SharedPreferences.
     */
    private const val KEY_TOKEN = "TOKEN"

    private lateinit var preferences: SharedPreferences
    /**
     * Initializes the TokenRepository with the given context. This must be called before using other methods.
     */

    fun init(context: Context) {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    /**
     * Retrieves the stored token.
     * @return The stored token or null if no token is found.
     */
    fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    /**
     * Stores the given token.
     */
    fun setToken(token: String) {
        preferences.edit().apply {
            putString(KEY_TOKEN, token)
            apply()
        }
    }

    /**
     * Removes the stored token.
     */
    fun removeToken() {
        preferences.edit().apply {
            remove(KEY_TOKEN)
            apply()
        }
    }
}