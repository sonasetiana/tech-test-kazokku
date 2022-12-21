package com.sonasetiana.techtestkozokku.data.remote

import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val service: ApiServices
) {
    suspend fun getUserList(
        page: Int = 0,
        limit: Int = 20
    ): Flow<ApiResponse<List<UserResponse>>> = flow {
        try {
            val response = service.getUserList(page = page, limit = limit)
            val data = response.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUserDetail(
        userId: String
    ): Flow<ApiResponse<UserDetailResponse>> = flow {
        try {
            val response = service.getUserDetail(userId = userId)
            emit(ApiResponse.Success(response))
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUserPost(
        userId: String
    ): Flow<ApiResponse<List<UserPostResponse>>> = flow {
        try {
            val response = service.getUserPost(userId = userId)
            val data = response.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllPost(): Flow<ApiResponse<List<UserPostResponse>>> = flow {
        try {
            val response = service.getAllPost()
            val data = response.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPostByTags(
        tagName: String
    ): Flow<ApiResponse<List<UserPostResponse>>> = flow {
        try {
            val response = service.getPostByTags(tagName = tagName)
            val data = response.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}