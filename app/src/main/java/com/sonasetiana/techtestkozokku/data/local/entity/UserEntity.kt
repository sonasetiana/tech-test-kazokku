package com.sonasetiana.techtestkozokku.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userId")
    var userId: String,
)
