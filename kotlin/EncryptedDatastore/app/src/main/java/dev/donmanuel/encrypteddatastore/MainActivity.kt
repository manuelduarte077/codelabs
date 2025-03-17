package dev.donmanuel.encrypteddatastore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import dev.donmanuel.encrypteddatastore.ui.theme.EncryptedDatastoreTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val Context.dataStore by dataStore(
    fileName = "user-preferences", serializer = UserPreferencesSerializer
)

private val SECRET_TOKEN = "Encrypted Text!"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EncryptedDatastoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        val scope = rememberCoroutineScope()
                        var text by remember { mutableStateOf("") }

                        Button(
                            onClick = {
                                scope.launch {
                                    dataStore.updateData {
                                        UserPreferences(
                                            token = SECRET_TOKEN
                                        )
                                    }
                                }
                            }) {
                            Text("Encrypt")
                        }


                        Button(
                            onClick = {
                                scope.launch {
                                    text = dataStore.data.first().token ?: ""
                                }
                            }) {
                            Text("Decrypt")
                        }

                        Text(
                            text = text
                        )
                    }
                }
            }

        }
    }
}

