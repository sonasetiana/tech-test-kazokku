package com.sonasetiana.techtestkozokku.domain.modules.timeline

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import kotlinx.coroutines.flow.Flow

interface TimeLineUseCase {
    fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>>
    fun getPostByTags(tagName: String, limit: Int?): Flow<PagingData<UserPostResponse>>
    fun checkFavorite(id: String): Flow<RoomResult<Boolean>>
    suspend fun saveFavorite(data: UserPostResponse)
    suspend fun deleteFavorite(data: UserPostResponse)
    suspend fun saveOwner(data: UserPostResponse)
    suspend fun deleteOwner(data: UserPostResponse)
}