package com.sonasetiana.techtestkozokku.domain.modules.detail

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>>
    fun getUserPost(userId: String, limit: Int?): Flow<PagingData<UserPostResponse>>
    fun checkUser(id: String): Flow<RoomResult<Boolean>>
    suspend fun saveUser(id: String)
    suspend fun deleteUser(id: String)
}