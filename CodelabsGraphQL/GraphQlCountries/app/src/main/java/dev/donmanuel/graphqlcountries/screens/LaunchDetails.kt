package dev.donmanuel.graphqlcountries.screens


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.apollographql.apollo.exception.ApolloNetworkException
import dev.donmanuel.graphqlcountries.BookTripMutation
import dev.donmanuel.graphqlcountries.CancelTripMutation
import dev.donmanuel.graphqlcountries.LaunchDetailsQuery
import dev.donmanuel.graphqlcountries.R
import dev.donmanuel.graphqlcountries.TokenRepository
import dev.donmanuel.graphqlcountries.apolloClient
import dev.donmanuel.graphqlcountries.screens.LaunchDetailsState.Loading
import dev.donmanuel.graphqlcountries.screens.LaunchDetailsState.Success
import dev.donmanuel.graphqlcountries.screens.LaunchDetailsState.Error
import kotlinx.coroutines.launch

private sealed interface LaunchDetailsState {
    object Loading : LaunchDetailsState
    data class Error(val message: String) : LaunchDetailsState
    data class Success(val data: LaunchDetailsQuery.Data) : LaunchDetailsState
}

@Composable
fun LaunchDetails(launchId: String, navigateToLogin: () -> Unit) {
    var state by remember { mutableStateOf<LaunchDetailsState>(Loading) }
    LaunchedEffect(Unit) {
        val response = apolloClient.query(LaunchDetailsQuery(launchId)).execute()
        state = when {
            response.errors.orEmpty().isNotEmpty() -> {
                // GraphQL error
                Error(response.errors!!.first().message)
            }

            response.exception is ApolloNetworkException -> {
                // Network error
                Error("Please check your network connectivity.")
            }

            response.data != null -> {
                // data (never partial)
                Success(response.data!!)
            }

            else -> {
                // Another fetch error, maybe a cache miss?
                // Or potentially a non-compliant server returning data: null without an error
                Error("Oh no... An error happened.")
            }
        }
    }
    when (val s = state) {
        Loading -> Loading()
        is Error -> ErrorMessage(s.message)
        is Success -> LaunchDetails(s.data, navigateToLogin)
    }
}

@Composable
private fun LaunchDetails(
    data: LaunchDetailsQuery.Data,
    navigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Mission patch
            AsyncImage(
                modifier = Modifier.size(160.dp, 160.dp),
                model = data.launch?.mission?.missionPatch,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Mission patch"
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                // Mission name
                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = data.launch?.mission?.name ?: ""
                )

                // Rocket name
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    text = data.launch?.rocket?.name?.let { "🚀 $it" } ?: "",
                )

                // Site
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = data.launch?.site ?: "",
                )
            }
        }
        // Book button
        var loading by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        var isBooked by remember { mutableStateOf(data.launch?.isBooked == true) }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            enabled = !loading,
            onClick = {
                loading = true
                scope.launch {
                    val ok = onBookButtonClick(
                        launchId = data.launch?.id ?: "",
                        isBooked = isBooked,
                        navigateToLogin = navigateToLogin
                    )
                    if (ok) {
                        isBooked = !isBooked
                    }
                    loading = false
                }
            }
        ) {
            if (loading) {
                SmallLoading()
            } else {
                Text(text = if (!isBooked) "Book now" else "Cancel booking")
            }
        }
    }
}

private suspend fun onBookButtonClick(
    launchId: String,
    isBooked: Boolean,
    navigateToLogin: () -> Unit
): Boolean {
    if (TokenRepository.getToken() == null) {
        navigateToLogin()
        return false
    }
    val mutation = if (isBooked) {
        CancelTripMutation(id = launchId)
    } else {
        BookTripMutation(id = launchId)
    }
    val response = apolloClient.mutation(mutation).execute()
    return if (response.data != null) {
        true
    } else {
        if (response.exception != null) {
            Log.w("LaunchDetails", "Failed to book/cancel trip", response.exception)
            false
        } else {
            Log.w("LaunchDetails", "Failed to book/cancel trip: ${response.errors!![0].message}")
            false
        }
    }
}

@Composable
private fun ErrorMessage(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SmallLoading() {
    CircularProgressIndicator(
        modifier = Modifier.size(24.dp),
        color = LocalContentColor.current,
        strokeWidth = 2.dp,
    )
}

@Preview(showBackground = true)
@Composable
private fun LaunchDetailsPreview() {
    LaunchDetails(launchId = "42", navigateToLogin = {})
}
