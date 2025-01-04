package com.user.info.ui.add_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.domain.entities.User
import com.user.domain.use_cases.AddUserUseCase
import com.user.info.state.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val useCase: AddUserUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _addUserStateFlow =
        MutableStateFlow<NetworkState<Unit>>(NetworkState.Idle)
    val addUserStateFlow get() = _addUserStateFlow

    fun addUser(user: User) {
        _addUserStateFlow.value = NetworkState.Loading
        viewModelScope.launch(dispatcher) {
            _addUserStateFlow.value = NetworkState.Result(useCase.invoke(user))
        }
    }


}