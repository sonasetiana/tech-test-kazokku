package com.sonasetiana.techtestkozokku.domain.modules.favorite

import com.sonasetiana.techtestkozokku.domain.repository.DataRepository

class FavoriteInteractor(
    private val repository: DataRepository
) : FavoriteUseCase {
}