package com.sonasetiana.techtestkozokku.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner")
data class OwnerEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ownerId")
    var ownerId: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "firstName")
    var firstName: String,
    @ColumnInfo(name = "lastName")
    var lastName: String,
    @ColumnInfo(name = "picture")
    var picture: String,
)
