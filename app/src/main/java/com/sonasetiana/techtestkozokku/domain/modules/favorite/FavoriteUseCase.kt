package com.sonasetiana.techtestkozokku.domain.modules.favorite

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getFavorite(): Flow<PagingData<UserPostResponse>>
    suspend fun saveFavorite(data: UserPostResponse)
    suspend fun deleteFavorite(data: UserPostResponse)
    suspend fun saveOwner(data: UserPostResponse)
    suspend fun deleteOwner(data: UserPostResponse)
    fun checkFavorite(id: String): Flow<RoomResult<Boolean>>
}