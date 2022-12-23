package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE userId=:userId")
    fun checkUser(userId: String): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(items: List<OwnerEntity>)

    @Query("DELETE FROM users WHERE userId = :userId")
    fun updateUser(userId: String)
}