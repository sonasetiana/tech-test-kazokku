package com.sonasetiana.techtestkozokku.presentation.modules.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.domain.modules.user.UserUseCase
import kotlinx.coroutines.flow.Flow

class UserViewModel(
    private val userUseCase: UserUseCase
) : ViewModel(){
    val userListState : Flow<PagingData<UserResponse>> = userUseCase.getUserList(10).cachedIn(viewModelScope)
}