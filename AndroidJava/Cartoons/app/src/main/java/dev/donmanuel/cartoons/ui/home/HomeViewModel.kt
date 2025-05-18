package dev.donmanuel.cartoons.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.donmanuel.cartoons.core.api.ApiClient
import dev.donmanuel.cartoons.core.api.ApiService
import dev.donmanuel.cartoons.model.Futurama
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Futurama Characters"
    }
    val text: LiveData<String> = _text

    private val _characters = MutableLiveData<List<Futurama>>()
    val characters: LiveData<List<Futurama>> = _characters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    private val apiService = ApiClient.createService(ApiService::class.java)

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        _isLoading.value = true
        apiService.getFuturamaCharacters().enqueue(object : Callback<List<Futurama>> {
            override fun onResponse(call: Call<List<Futurama>>, response: Response<List<Futurama>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _characters.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Futurama>>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message ?: "Unknown error"
            }
        })
    }
}