package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.room.*
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE favoriteId = :favoriteId")
    fun checkFavorite(favoriteId: String): Flow<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(items: List<FavoriteEntity>)

    @Update
    fun updateFavorite(item: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE favoriteId = :favoriteId")
    fun deleteFavorite(favoriteId: String)
}