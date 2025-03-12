package dev.donmanuel.datastorepreferencescompose

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserSessionManager(
    context: Context
) {

    private val dataStore = context.dataStore

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean, keepLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            if (keepLoggedIn) {
                preferences[IS_LOGGED_IN] = isLoggedIn
            } else {
                preferences[IS_LOGGED_IN] = false
            }
        }
    }

    fun getLoggedInStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    }
}