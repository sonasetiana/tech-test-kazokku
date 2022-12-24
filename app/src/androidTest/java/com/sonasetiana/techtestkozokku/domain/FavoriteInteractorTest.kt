package com.sonasetiana.techtestkozokku.domain

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.modules.favorite.FavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FavoriteInteractorTest : FavoriteUseCase {
    override fun getFavorite(): Flow<PagingData<UserPostResponse>> = flowOf(PagingData.from(DummyData.postItems))

    override suspend fun deleteFavorite(data: UserPostResponse) {
        
    }

    override suspend fun deleteOwner(data: UserPostResponse) {
        
    }

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = flowOf(RoomResult.Success(true))
}