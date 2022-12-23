package com.sonasetiana.techtestkozokku.data.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "postId")
    var postId: String,
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

data class FavoriteRelationOwner(
    @Embedded
    val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "postId",
        entityColumn = "postId"
    )
    val owner: OwnerEntity? = null
)