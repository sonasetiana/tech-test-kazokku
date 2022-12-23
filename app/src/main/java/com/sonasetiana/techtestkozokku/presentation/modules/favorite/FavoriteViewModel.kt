package com.sonasetiana.techtestkozokku.presentation.modules.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.modules.favorite.FavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val useCase: FavoriteUseCase
) : ViewModel() {
    val favorites : Flow<PagingData<UserPostResponse>> = useCase.getFavorite().cachedIn(viewModelScope)

    fun checkFavorite(userId: String): Flow<RoomResult<Boolean>> = useCase.checkFavorite(userId)

    fun saveFavorite(data: UserPostResponse) {
        viewModelScope.launch {
            useCase.saveFavorite(data)
            useCase.saveOwner(data)
        }
    }

    fun deleteFavorite(data: UserPostResponse) {
        viewModelScope.launch {
            useCase.deleteFavorite(data)
            useCase.deleteOwner(data)
        }
    }
}