package dev.donmanuel.cartoons.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.donmanuel.cartoons.model.Simpsons
import dev.donmanuel.cartoons.core.api.ApiClient
import dev.donmanuel.cartoons.core.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "The Simpsons Episodes"
    }
    val text: LiveData<String> = _text

    private val _episodes = MutableLiveData<List<Simpsons>>()
    val episodes: LiveData<List<Simpsons>> = _episodes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error

    private val apiService = ApiClient.createService(ApiService::class.java)

    init {
        fetchSimpsonsEpisodes()
    }

    private fun fetchSimpsonsEpisodes() {
        _isLoading.value = true
        _error.value = null

        apiService.getSimpsonsEpisodes().enqueue(object : Callback<List<Simpsons>> {
            override fun onResponse(
                call: Call<List<Simpsons>>,
                response: Response<List<Simpsons>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _episodes.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Simpsons>>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Network error: ${t.message}"
            }
        })
    }
}