package com.sonasetiana.techtestkozokku.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiServices
import com.sonasetiana.techtestkozokku.data.remote.paging.AllPostPagingSource
import com.sonasetiana.techtestkozokku.data.remote.paging.TagsPostPagingSource
import com.sonasetiana.techtestkozokku.data.remote.paging.UserListPagingSource
import com.sonasetiana.techtestkozokku.data.remote.paging.UserPostPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl(
    private val service: ApiServices
) : RemoteDataSource{
    override fun getUserList(limit: Int?): Flow<PagingData<UserResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                UserListPagingSource(service, limit ?: 10)
            }
        ).flow
    }

    override suspend fun getUserDetail(
        userId: String
    ): Flow<ApiResponse<UserDetailResponse>> = flow {
        try {
            val response = service.getUserDetail(userId = userId)
            emit(ApiResponse.Success(response))
        }catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserPost(
        userId: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit ?: 20
            ),
            pagingSourceFactory = {
                UserPostPagingSource(service, userId)
            }
        ).flow
    }

    override suspend fun getAllPost(
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit ?: 20
            ),
            pagingSourceFactory = {
                AllPostPagingSource(service)
            }
        ).flow
    }

    override suspend fun getPostByTags(
        tagName: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit ?: 20
            ),
            pagingSourceFactory = {
                TagsPostPagingSource(service, tagName)
            }
        ).flow
    }
}