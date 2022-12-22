package com.sonasetiana.techtestkozokku.data.repository

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.datasource.RemoteDataSource
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : DataRepository {
    override fun getUserList(limit: Int?): Flow<PagingData<UserResponse>> = remoteDataSource.getUserList(limit)

    override suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>> = remoteDataSource.getUserDetail(userId)

    override suspend fun getUserPost(
        userId: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = remoteDataSource.getUserPost(userId, limit)

    override suspend fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>> = remoteDataSource.getAllPost(limit)

    override suspend fun getPostByTags(
        tagName: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = remoteDataSource.getPostByTags(tagName, limit)

}