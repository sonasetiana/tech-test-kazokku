package com.sonasetiana.techtestkozokku.presentation.modules.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.modules.detail.DetailUseCase
import com.sonasetiana.techtestkozokku.presentation.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: DetailUseCase
) : ViewModel(){
    private val _uiState : MutableStateFlow<UiState<UserDetailResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserDetailResponse>>
        get() = _uiState

    var timeLine: Flow<PagingData<UserPostResponse>>? = null

    fun getDetail(userId: String) {
        viewModelScope.launch {
            useCase.getUserDetail(userId)
                .collect {
                    when(it) {
                        is ApiResponse.Success -> {
                            _uiState.value = UiState.Success(it.data)
                            timeLine = useCase.getUserPost(userId = userId, limit = 20).cachedIn(viewModelScope)
                        }
                        is ApiResponse.Error -> {
                            _uiState.value = UiState.Error(it.message)
                        }
                    }
                }
        }
    }

}