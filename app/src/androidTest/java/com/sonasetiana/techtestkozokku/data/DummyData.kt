package com.sonasetiana.techtestkozokku.data

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserDetailLocation
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DummyData {
    val userItems = listOf(
        UserResponse(id = "60d0fe4f5311236168a109d4", title = "mr", firstName = "Valentin", lastName = "Ortega", picture = "https://randomuser.me/api/portraits/med/men/3.jpg"),
        UserResponse(id = "60d0fe4f5311236168a109d5", title = "mrs", firstName = "Sibylle", lastName = "Leibold", picture = "https://randomuser.me/api/portraits/med/women/89.jpg"),
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

    val postItems = listOf(
        UserPostResponse(
            id = "60d21b4667d0d8992e610c85",
            image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
            publishDate = "2020-05-24T14:53:17.598Z",
            text = "adult Labrador retriever",
            likes = 43,
            tags = listOf(
                "animal",
                "dog",
                "golden retriever",
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109ca",
                title = "ms",
                firstName = "Sara",
                lastName = "Andersen",
                picture = "https://randomuser.me/api/portraits/med/women/58.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21b4967d0d8992e610c90",
            image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
            publishDate = "2020-05-24T07:44:17.738Z",
            text = "ice caves in the wild landscape photo of ice near ...",
            likes = 31,
            tags = listOf(
                "snow",
                "ice",
                "mountain"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a10a0b",
                title = "miss",
                firstName = "Margarita",
                lastName = "Vicente",
                picture = "https://randomuser.me/api/portraits/med/women/5.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21b8667d0d8992e610d3f",
            image = "https://img.dummyapi.io/photo-1515376721779-7db6951da88d.jpg",
            publishDate = "2020-05-24T05:44:55.297Z",
            text = "@adventure.yuki frozen grass short-coated black do...",
            likes = 16,
            tags = listOf(
                "dog",
                "pet",
                "canine"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109ed",
                title = "miss",
                firstName = "Kayla",
                lastName = "Bredesen",
                picture = "https://randomuser.me/api/portraits/med/women/13.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21b3a67d0d8992e610c60",
            image = "https://img.dummyapi.io/photo-1581804928342-4e3405e39c91.jpg",
            publishDate = "2020-05-23T22:56:11.424Z",
            text = "Hiking with my dog in the woods. black labrador re...",
            likes = 7,
            tags = listOf(
                "canine",
                "pet",
                "mammal"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d5",
                title = "mrs",
                firstName = "Sibylle",
                lastName = "Leibold",
                picture = "https://randomuser.me/api/portraits/med/women/89.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21bf967d0d8992e610e9b",
            image = "https://img.dummyapi.io/photo-1574457547512-5b1646994eea.jpg",
            publishDate = "2020-05-23T18:52:32.613Z",
            text = "Two boys hug their dogs in a leaf pile in the fall...",
            likes = 28,
            tags = listOf(
                "dog",
                "human",
                "animal"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109f7",
                title = "mrs",
                firstName = "Jolanda",
                lastName = "Lacroix",
                picture = "https://randomuser.me/api/portraits/med/women/32.jpg"
            )
        )
    )

    val dataUserDetail = UserDetailResponse(
        id = "60d0fe4f5311236168a109d4",
        title = "mr",
        firstName = "Valentin",
        lastName = "Ortega",
        gender = "male",
        phone = "993-465-335",
        email = "valentin.ortega@example.com",
        picture = "https://randomuser.me/api/portraits/med/men/3.jpg",
        dateOfBirth = "953-10-15T02:26:17.794Z",
        updatedDate = "2021-06-21T21:02:09.050Z",
        registerDate = "2021-06-21T21:02:09.050Z",
        location = UserDetailLocation(
            street = "2580, Calle de Ferraz",
            city = "Albacete",
            country = "Spain",
            state = "Islas Baleares",
            timezone = "-3:30",
        )
    )

    val userPostItems = listOf(
        UserPostResponse(
            id = "60d21baf67d0d8992e610db6",
            image = "https://img.dummyapi.io/photo-1543554536-0d236466160b.jpg",
            publishDate = "2020-04-20T00:49:59.841Z",
            text = "long-coated tan dog lying on floor",
            likes = 27,
            tags = listOf(
                "golden retriever",
                "dog",
                "animal"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d4",
                title = "mr",
                firstName = "Valentin",
                lastName = "Ortega",
                picture = "https://randomuser.me/api/portraits/med/men/3.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21bd367d0d8992e610e25",
            image = "https://img.dummyapi.io/photo-1556877986-d40833ec88b4.jpg",
            publishDate = "2020-05-19T02:16:25.689Z",
            text = "Being a proud owner of a frenchie... That means lo...",
            likes = 6,
            tags = listOf(
                "dog",
                "canine",
                "animal"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d4",
                title = "mr",
                firstName = "Valentin",
                lastName = "Ortega",
                picture = "https://randomuser.me/api/portraits/med/men/3.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21b7167d0d8992e610d05",
            image = "https://img.dummyapi.io/photo-1534822916-212967756e82.jpg",
            publishDate = "2020-03-27T13:22:09.681Z",
            text = "Pet’s happinness short-coated brown and white dog ...",
            likes = 183,
            tags = listOf(
                "animal",
                "dog",
                "pet"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d4",
                title = "mr",
                firstName = "Valentin",
                lastName = "Ortega",
                picture = "https://randomuser.me/api/portraits/med/men/3.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21ae967d0d8992e610b73",
            image = "https://img.dummyapi.io/photo-1545333781-e26c2af46ff1.jpg",
            publishDate = "2020-03-25T12:14:01.633Z",
            text = "Carefree white puddle",
            likes = 72,
            tags = listOf(
                "dog",
                "animal",
                "canine"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d4",
                title = "mr",
                firstName = "Valentin",
                lastName = "Ortega",
                picture = "https://randomuser.me/api/portraits/med/men/3.jpg"
            )
        ),
        UserPostResponse(
            id = "60d21be967d0d8992e610e69",
            image = "https://img.dummyapi.io/photo-1529259646408-5c50deb4cf6a.jpg",
            text = "What are you looking for? grayscale photo of opene...",
            publishDate = "2020-02-10T18:37:20.729Z",
            likes = 136,
            tags = listOf(
                "dog",
                "black-and-white",
                "tongue"
            ),
            owner = UserResponse(
                id = "60d0fe4f5311236168a109d4",
                title = "mr",
                firstName = "Valentin",
                lastName = "Ortega",
                picture = "https://randomuser.me/api/portraits/med/men/3.jpg"
            )
        )
    )

}