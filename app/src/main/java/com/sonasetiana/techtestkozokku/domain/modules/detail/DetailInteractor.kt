package com.sonasetiana.techtestkozokku.domain.modules.detail

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(
    private val repository: DataRepository
) : DetailUseCase {
    override suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>> = repository.getUserDetail(userId)

    override fun getUserPost(
        userId: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = repository.getUserPost(userId, limit)
}