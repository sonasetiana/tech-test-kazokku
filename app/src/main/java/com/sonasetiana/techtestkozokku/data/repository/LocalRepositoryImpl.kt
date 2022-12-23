package com.sonasetiana.techtestkozokku.data.repository

import com.sonasetiana.techtestkozokku.data.local.datasource.LocalDataSource
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalRepository{
    override fun getAllFavorite(): Flow<List<FavoriteEntity>> = localDataSource.getAllFavorite()

    override fun checkFavorite(id: String): Flow<FavoriteEntity> = localDataSource.checkFavorite(id)

    override fun checkUser(id: String): Flow<RoomResult<Boolean>> = localDataSource.checkUser(id)

    override suspend fun saveUser(id: String) {
        localDataSource.saveUser(id)
    }

    override fun getOwner(id: String): Flow<OwnerEntity> = getOwner(id)

    override suspend fun deleteFavorite(id: String) = localDataSource.deleteFavorite(id)

    override suspend fun deleteUser(id: String) = localDataSource.deleteUser(id)

    override suspend fun deleteOwner(id: String) = localDataSource.deleteOwner(id)
}