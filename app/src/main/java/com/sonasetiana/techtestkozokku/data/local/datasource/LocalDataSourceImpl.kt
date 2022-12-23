package com.sonasetiana.techtestkozokku.data.local.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.dao.FavoriteDao
import com.sonasetiana.techtestkozokku.data.local.dao.OwnerDao
import com.sonasetiana.techtestkozokku.data.local.dao.UserDao
import com.sonasetiana.techtestkozokku.data.local.db.RoomMapping
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteRelationOwner
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSourceImpl(
    private val favoriteDao: FavoriteDao,
    private val userDao: UserDao,
    private val ownerDao: OwnerDao,
) : LocalDataSource{
    override fun getAllFavorite(): Flow<PagingData<FavoriteRelationOwner>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                favoriteDao.getFavorites()
            }
        ).flow
    }

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = flow {
        try {
            favoriteDao.checkFavorite(id).collect {
                emit(RoomResult.Success(it.isNotEmpty()))
            }
        }catch (e: Exception) {
            emit(RoomResult.Failure(e.localizedMessage.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun checkUser(id: String): Flow<RoomResult<Boolean>> = flow {
        try {
            userDao.checkUser(id).collect {
                emit(RoomResult.Success(it.isNotEmpty()))
            }
        }catch (e: Exception) {
            emit(RoomResult.Failure(e.localizedMessage.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getOwner(id: String): Flow<OwnerEntity> = ownerDao.getOwner(id)

    override suspend fun saveUser(id: String) {
        userDao.insertUser(UserEntity(id))
    }

    override suspend fun saveFavorite(data: UserPostResponse) {
        favoriteDao.insertFavorite(RoomMapping.mappingDataFavorite(data))
    }

    override suspend fun deleteFavorite(data: UserPostResponse) {
        favoriteDao.deleteFavorite(RoomMapping.mappingDataFavorite(data))
    }

    override suspend fun deleteUser(id: String) {
        userDao.deleteUser(UserEntity(id))
    }

    override suspend fun saveOwner(data: UserPostResponse) {
        ownerDao.insertOwner(RoomMapping.mappingDataOwner(data))
    }

    override suspend fun deleteOwner(data: UserPostResponse) {
        ownerDao.deleteOwner(RoomMapping.mappingDataOwner(data))
    }
}