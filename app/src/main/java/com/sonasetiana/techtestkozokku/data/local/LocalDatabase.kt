package com.sonasetiana.techtestkozokku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sonasetiana.techtestkozokku.data.local.dao.FavoriteDao
import com.sonasetiana.techtestkozokku.data.local.dao.OwnerDao
import com.sonasetiana.techtestkozokku.data.local.dao.TagDao
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.TagEntity

@Database(entities = [FavoriteEntity::class,
    TagEntity::class,
    OwnerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
    abstract fun tagDao(): TagDao
    abstract fun ownerDao(): OwnerDao
}