package com.sonasetiana.techtestkozokku.data.local.datasource

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteRelationOwner
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import kotlinx.coroutines.flow.Flow

interface LocalDataSource{
    fun getAllFavorite(): Flow<PagingData<FavoriteRelationOwner>>

    fun checkFavorite(id: String): Flow<RoomResult<Boolean>>

    fun checkUser(id: String): Flow<RoomResult<Boolean>>

    fun getOwner(id: String): Flow<OwnerEntity>

    suspend fun saveUser(id: String)

    suspend fun deleteUser(id: String)

    suspend fun saveFavorite(data: UserPostResponse)

    suspend fun deleteFavorite(data: UserPostResponse)

    suspend fun saveOwner(data: UserPostResponse)

    suspend fun deleteOwner(data: UserPostResponse)
}