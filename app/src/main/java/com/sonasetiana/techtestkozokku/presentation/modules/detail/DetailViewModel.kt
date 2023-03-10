package com.sonasetiana.techtestkozokku.presentation.modules.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
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

    fun checkUser(userId: String): Flow<RoomResult<Boolean>> = useCase.checkUser(userId)

    fun saveUser(userId: String) {
        viewModelScope.launch {
            useCase.saveUser(userId)
        }
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch {
            useCase.deleteUser(userId)
        }
    }

    fun getDetail(userId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
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

    fun checkFavorite(userId: String): Flow<RoomResult<Boolean>> = useCase.checkFavorite(userId)

    fun saveFavorite(data: UserPostResponse) {
        viewModelScope.launch {
            useCase.saveFavorite(data)
            useCase.saveOwner(data)
        }
    }

    fun deleteFavorite(data: UserPostResponse) {
        viewModelScope.launch {
            useCase.deleteFavorite(data)
            useCase.deleteOwner(data)
        }
    }

}