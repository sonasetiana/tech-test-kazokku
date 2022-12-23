package com.sonasetiana.techtestkozokku.data.local.db

import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteEntity
import com.sonasetiana.techtestkozokku.data.local.entity.FavoriteRelationOwner
import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.model.UserResponse

object RoomMapping {
    fun mappingDataFavorite(data: UserPostResponse) : FavoriteEntity {
        return FavoriteEntity(
            postId = data.id.orEmpty(),
            image = data.image.orEmpty(),
            title = data.text.orEmpty(),
            publishDate = data.publishDate.orEmpty(),
            likes = data.likes ?: 0,
            tags = data.tags.joinToString()
        )
    }

    fun mappingDataOwner(data: UserPostResponse) : OwnerEntity {
        return OwnerEntity(
            postId = data.id.orEmpty(),
            ownerId = data.owner?.id.orEmpty(),
            title = data.owner?.title.orEmpty(),
            firstName = data.owner?.firstName.orEmpty(),
            lastName = data.owner?.lastName.orEmpty(),
            picture = data.owner?.picture.orEmpty()
        )
    }

    fun mappingFavoriteRelation(data: FavoriteRelationOwner): UserPostResponse {
        return UserPostResponse(
            id = data.favorite.postId,
            image = data.favorite.image,
            publishDate = data.favorite.publishDate,
            text = data.favorite.title,
            tags = data.favorite.tags.split(","),
            likes = data.favorite.likes,
            owner = UserResponse(
                id = data.owner?.ownerId,
                title = data.owner?.title,
                firstName = data.owner?.firstName,
                lastName = data.owner?.lastName,
                picture = data.owner?.picture
            )
        )
    }
}