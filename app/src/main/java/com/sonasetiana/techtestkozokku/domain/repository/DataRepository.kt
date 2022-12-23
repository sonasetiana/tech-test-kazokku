package com.sonasetiana.techtestkozokku.domain.repository

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getUserList(limit: Int?): Flow<PagingData<UserResponse>>
    suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>>
    fun getUserPost(userId: String, limit: Int?): Flow<PagingData<UserPostResponse>>
    fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>>
    fun getPostByTags(tagName: String, limit: Int?): Flow<PagingData<UserPostResponse>>
}