package com.sonasetiana.techtestkozokku.domain.modules.favorite

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(
    private val repository: LocalRepository
) : FavoriteUseCase {
    override fun getFavorite(): Flow<PagingData<UserPostResponse>> = repository.getAllFavorite()

    override suspend fun saveFavorite(data: UserPostResponse) = repository.saveFavorite(data)

    override suspend fun deleteFavorite(data: UserPostResponse) = repository.deleteFavorite(data)

    override suspend fun saveOwner(data: UserPostResponse) = repository.saveOwner(data)

    override suspend fun deleteOwner(data: UserPostResponse) = repository.deleteOwner(data)

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = repository.checkFavorite(id)
}