package com.sonasetiana.techtestkozokku.domain

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.domain.modules.user.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserInteractorTest : UserUseCase {
    override fun getUserList(limit: Int?): Flow<PagingData<UserResponse>> = flow {
        delay(1000)
        emit(PagingData.from(DummyData.userItems))
    }
}