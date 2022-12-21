package com.sonasetiana.techtestkozokku.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tagId")
    var tagId: String,
    @ColumnInfo(name = "name")
    var name: String,
)
