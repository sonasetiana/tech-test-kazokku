package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteRelationOwner
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Transaction
    @Query("SELECT * FROM favorite")
    fun getFavorites(): PagingSource<Int, FavoriteRelationOwner>

    @Query("SELECT * FROM favorite WHERE postId = :postId")
    fun checkFavorite(postId: String): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(items: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteEntity)

    @Update
    fun updateFavorite(item: FavoriteEntity)
}