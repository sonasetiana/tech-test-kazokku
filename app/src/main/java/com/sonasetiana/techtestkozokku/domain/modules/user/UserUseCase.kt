package com.sonasetiana.techtestkozokku.domain.modules.user

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getUserList(limit: Int?): Flow<PagingData<UserResponse>>
}