package com.sonasetiana.techtestkozokku.data.local

import com.sonasetiana.techtestkozokku.data.local.dao.FavoriteDao
import com.sonasetiana.techtestkozokku.data.local.dao.OwnerDao
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.TagEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val favoriteDao: FavoriteDao,
    private val tagDao: TagDao,
    private val ownerDao: OwnerDao
) {
    fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getFavorites()

    fun getTags(id: String): Flow<List<TagEntity>> = tagDao.getTags(id)

    fun getOwner(id: String): Flow<OwnerEntity> = ownerDao.getOwner(id)
}