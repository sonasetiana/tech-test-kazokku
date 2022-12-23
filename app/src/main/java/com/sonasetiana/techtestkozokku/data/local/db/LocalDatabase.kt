package com.sonasetiana.techtestkozokku.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sonasetiana.techtestkozokku.data.local.dao.FavoriteDao
import com.sonasetiana.techtestkozokku.data.local.dao.OwnerDao
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity

@Database(
    entities = [
        FavoriteEntity::class,
        OwnerEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
    abstract fun ownerDao(): OwnerDao
}