package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.room.*
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {
    @Query("SELECT * FROM owner WHERE ownerId=:ownerId")
    fun getOwner(ownerId: String): Flow<OwnerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(items: OwnerEntity)

    @Update
    fun updateOwner(item: OwnerEntity)

    @Delete
    suspend fun deleteOwner(entity: OwnerEntity)
}