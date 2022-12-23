package com.sonasetiana.techtestkozokku.data.local.dao

import androidx.room.*
import com.sonasetiana.techtestkozokku.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE userId=:userId")
    fun checkUser(userId: String): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(items: UserEntity)

    @Delete
    suspend fun deleteUser(entity: UserEntity)
}