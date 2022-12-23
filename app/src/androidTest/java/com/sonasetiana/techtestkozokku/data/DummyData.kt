package com.sonasetiana.techtestkozokku.data

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DummyData {
    val userItems = listOf<UserResponse>(
        UserResponse(id = "60d0fe4f5311236168a109d4", title = "mr", firstName = "Valentin", lastName = "Ortega", picture = "https://randomuser.me/api/portraits/med/men/3.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109d5", title = ",mrs", firstName = "Sibylle", lastName = "Leibold", picture = "https://randomuser.me/api/portraits/med/women/89.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109d7", title = "mr", firstName = "Leevi", lastName = "Savela", picture = "https://randomuser.me/api/portraits/med/men/67.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109d8", title = "mrs", firstName = "Karoline", lastName = "Sviggum", picture = "https://randomuser.me/api/portraits/med/women/67.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109d9", title = "ms", firstName = "Nuria", lastName = "Leon", picture = "https://randomuser.me/api/portraits/med/women/63.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109da", title = "mr", firstName = "Lance", lastName = "Foster", picture = "https://randomuser.me/api/portraits/med/men/13.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109db", title = "miss", firstName = "Naomi", lastName = "Rodrigues", picture = "https://randomuser.me/api/portraits/med/women/39.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109dc", title = "mr", firstName = "Evan", lastName = "Roux", picture = "https://randomuser.me/api/portraits/med/men/59.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109dd", title = "mr", firstName = "Miguel", lastName = "Lima", picture = "https://randomuser.me/api/portraits/med/men/31.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109ca", title = "ms", firstName = "Sara", lastName = "Andersen", picture = "https://randomuser.me/api/portraits/med/women/58.jpg"),
    )

    fun dataListUser(): Flow<PagingData<UserResponse>> = flowOf(PagingData.from(userItems))

}