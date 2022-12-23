package com.sonasetiana.techtestkozokku.data.local.datasource

import com.sonasetiana.techtestkozokku.data.local.dao.FavoriteDao
import com.sonasetiana.techtestkozokku.data.local.dao.OwnerDao
import com.sonasetiana.techtestkozokku.data.local.dao.UserDao
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSourceImpl(
    private val favoriteDao: FavoriteDao,
    private val userDao: UserDao,
    private val ownerDao: OwnerDao,
) : LocalDataSource{
    override fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getFavorites()

    override fun checkFavorite(id: String): Flow<FavoriteEntity> = favoriteDao.checkFavorite(id)

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

    override suspend fun deleteFavorite(id: String) {
        favoriteDao.deleteFavorite(id)
    }

    override suspend fun deleteUser(id: String) {
        userDao.deleteUser(UserEntity(id))
    }

    override suspend fun deleteOwner(id: String) {
        ownerDao.deleteOwner(id)
    }
}