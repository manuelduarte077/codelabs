package dev.donmanuel.dicompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.dicompose.model.UserResponseItem
import dev.donmanuel.dicompose.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _userList = MutableStateFlow(emptyList<UserResponseItem>())
    val userList: StateFlow<List<UserResponseItem>> get() = _userList

    init {
        viewModelScope.launch {
            _userList.value = repository.getUsers()
        }
    }

}