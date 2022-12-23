package com.sonasetiana.techtestkozokku.data.repository

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.datasource.RemoteDataSource
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {
    override fun getUserList(limit: Int?): Flow<PagingData<UserResponse>> = remoteDataSource.getUserList(limit)

    override suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>> = remoteDataSource.getUserDetail(userId)

    override fun getUserPost(
        userId: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = remoteDataSource.getUserPost(userId, limit)

    override fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>> = remoteDataSource.getAllPost(limit)

    override fun getPostByTags(
        tagName: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = remoteDataSource.getPostByTags(tagName, limit)

}