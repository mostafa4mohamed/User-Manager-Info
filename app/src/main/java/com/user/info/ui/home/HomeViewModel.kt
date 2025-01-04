package com.user.info.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.domain.entities.User
import com.user.domain.use_cases.GetAllUsersUseCase
import com.user.info.state.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetAllUsersUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _usersStateFlow =
        MutableStateFlow<NetworkState<List<User>>>(NetworkState.Idle)
    val usersStateFlow get() = _usersStateFlow

    fun getAllUsers() {
        _usersStateFlow.value = NetworkState.Loading
        viewModelScope.launch(dispatcher) {
            _usersStateFlow.value = NetworkState.Result(useCase.invoke())
        }
    }


}