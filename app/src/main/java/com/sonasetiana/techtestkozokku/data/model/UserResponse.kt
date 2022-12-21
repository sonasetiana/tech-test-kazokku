package com.sonasetiana.techtestkozokku.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("firstName")
    val firstName: String? = null,
    @field:SerializedName("lastName")
    val lastName: String? = null,
    @field:SerializedName("picture")
    val picture: String? = null,
)

data class ListUserResponse(
    @field:SerializedName("data")
    val data: List<UserResponse>
)