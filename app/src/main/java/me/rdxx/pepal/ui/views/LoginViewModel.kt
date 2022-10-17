package me.rdxx.pepal.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.rdxx.pepal.data.repositories.PepalRepo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pepalRepo: PepalRepo
) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state: StateFlow<String>
        get() = _state

    init {
        viewModelScope.launch {
            val response = pepalRepo.login()
        }
    }
}