package com.user.info.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.domain.entities.User
import com.user.domain.use_cases.UserDetailsUseCase
import com.user.info.state.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val useCase: UserDetailsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userDetailsStateFlow =
        MutableStateFlow<NetworkState<User?>>(NetworkState.Idle)
    val userDetailsStateFlow get() = _userDetailsStateFlow

    fun userDetails(userId: Int) {
        _userDetailsStateFlow.value = NetworkState.Loading
        viewModelScope.launch(dispatcher) {
            _userDetailsStateFlow.value = NetworkState.Result(useCase.invoke(userId))
        }
    }


}