package com.sonasetiana.techtestkozokku.domain.modules.detail

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>>
    suspend fun getUserPost(userId: String, limit: Int?): Flow<PagingData<UserPostResponse>>
}