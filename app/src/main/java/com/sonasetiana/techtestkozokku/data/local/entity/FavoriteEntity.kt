package com.sonasetiana.techtestkozokku.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "favoriteId")
    var favoriteId: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "publishDate")
    var publishDate: String,
    @ColumnInfo(name = "likes")
    var likes: Int,
    @ColumnInfo(name = "tags")
    var tags: String,
)
