package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.room.*
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(items: List<FavoriteEntity>)

    @Update
    fun updateFavorite(item: FavoriteEntity)
}