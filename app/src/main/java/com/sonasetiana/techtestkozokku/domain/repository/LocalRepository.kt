package com.sonasetiana.techtestkozokku.domain.repository

import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    fun checkFavorite(id: String): Flow<FavoriteEntity>

    fun checkUser(id: String): Flow<RoomResult<Boolean>>

    fun getOwner(id: String): Flow<OwnerEntity>

    suspend fun saveUser(id: String)

    suspend fun deleteFavorite(id: String)

    suspend fun deleteUser(id: String)

    suspend fun deleteOwner(id: String)
}