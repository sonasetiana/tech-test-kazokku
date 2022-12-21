package com.sonasetiana.techtestkozokku.data.model

import com.google.gson.annotations.SerializedName

data class UserPostResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("tags")
	val tags: List<String>,

	@field:SerializedName("owner")
	val owner: UserResponse? = null
)

data class ListUserPostResponse(
	@field:SerializedName("data")
	val data: List<UserPostResponse>
)

