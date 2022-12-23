package com.sonasetiana.techtestkozokku.domain.modules.favorite

import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository

class FavoriteInteractor(
    private val repository: RemoteRepository
) : FavoriteUseCase {
}