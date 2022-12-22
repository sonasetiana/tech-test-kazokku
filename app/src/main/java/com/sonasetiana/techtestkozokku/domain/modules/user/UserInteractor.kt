package com.sonasetiana.techtestkozokku.domain.modules.user

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(
    private val repository: DataRepository
) : UserUseCase {
    override fun getUserList(limit: Int?): Flow<PagingData<UserResponse>> = repository.getUserList(limit)
}