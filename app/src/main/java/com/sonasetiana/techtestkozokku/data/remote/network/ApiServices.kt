package com.sonasetiana.techtestkozokku.data.remote.network

import com.sonasetiana.techtestkozokku.data.model.ListUserPostResponse
import com.sonasetiana.techtestkozokku.data.model.ListUserResponse
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("user")
    suspend fun getUserList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): ListUserResponse

    @GET("user/{id}")
    suspend fun getUserDetail(
        @Path("id") userId: String
    ): UserDetailResponse

    @GET("user/{id}/post")
    suspend fun getUserPost(
        @Path("id") userId: String
    ): ListUserPostResponse

    @GET("post")
    suspend fun getAllPost(): ListUserPostResponse

    @GET("tag/{tagName}/post")
    suspend fun getPostByTags(
        @Path("tagName") tagName: String
    ): ListUserPostResponse
}