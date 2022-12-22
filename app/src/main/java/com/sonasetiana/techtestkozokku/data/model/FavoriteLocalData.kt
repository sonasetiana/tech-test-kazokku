package com.sonasetiana.techtestkozokku.data.model

import com.sonasetiana.techtestkozokku.data.local.entity.OwnerEntity

data class FavoriteLocalData(
    var favoriteId: String,
    var image: String,
    var title: String,
    var publishDate: String,
    var likes: Int,
    var tags: List<String>,
    var owner: OwnerEntity
)
