package com.sonasetiana.techtestkozokku.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sonasetiana.techtestkozokku.data.local.datasource.LocalDataSource
import com.sonasetiana.techtestkozokku.data.local.db.RoomMapping
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalRepository{
    override fun getAllFavorite(): Flow<PagingData<UserPostResponse>> = localDataSource.getAllFavorite().map { pagingData ->
        pagingData.map { RoomMapping.mappingFavoriteRelation(it) }
    }

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = localDataSource.checkFavorite(id)

    override fun checkUser(id: String): Flow<RoomResult<Boolean>> = localDataSource.checkUser(id)

    override suspend fun saveUser(id: String) {
        localDataSource.saveUser(id)
    }

    override fun getOwner(id: String): Flow<OwnerEntity> = getOwner(id)

    override suspend fun deleteUser(id: String) = localDataSource.deleteUser(id)

    override suspend fun saveFavorite(data: UserPostResponse) = localDataSource.saveFavorite(data)

    override suspend fun deleteFavorite(data: UserPostResponse) = localDataSource.deleteFavorite(data)

    override suspend fun saveOwner(data: UserPostResponse) = localDataSource.saveOwner(data)

    override suspend fun deleteOwner(data: UserPostResponse) = localDataSource.deleteOwner(data)

}