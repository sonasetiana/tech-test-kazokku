package com.sonasetiana.techtestkozokku.data.model

import com.google.gson.annotations.SerializedName
import com.sonasetiana.techtestkozokku.utils.DateExt
import com.sonasetiana.techtestkozokku.utils.upFirst

data class UserDetailResponse(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("dateOfBirth")
	val dateOfBirth: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("updatedDate")
	val updatedDate: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("picture")
	val picture: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("registerDate")
	val registerDate: String? = null,

	@field:SerializedName("location")
	val location: UserDetailLocation? = null
){
	val joinDate: String
		get() = DateExt.parseDate(registerDate)

	val dateOfBirthFormatted: String
		get() = DateExt.parseDate(dateOfBirth)

	val updatedDateFormatted: String
		get() = DateExt.parseDate(updatedDate)

	val fullName: String
		get() = "${title.upFirst()} ${firstName.upFirst()} ${lastName.upFirst()}"

	val address: String
		get() = "${location?.country}, ${location?.street}, ${location?.city}, ${location?.state}"
}

data class UserDetailLocation(

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,
)