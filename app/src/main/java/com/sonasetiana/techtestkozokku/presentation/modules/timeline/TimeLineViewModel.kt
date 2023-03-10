package com.sonasetiana.techtestkozokku.presentation.modules.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TimeLineViewModel(
    private val useCase: TimeLineUseCase
) : ViewModel(){
    var timeLines : Flow<PagingData<UserPostResponse>> = useCase.getAllPost(20).cachedIn(viewModelScope)

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

    fun setTagName(tagName: String) {
        timeLines = if (tagName.isEmpty()) {
            useCase.getAllPost(20).cachedIn(viewModelScope)
        } else {
            useCase.getPostByTags(tagName, limit = 20).cachedIn(viewModelScope)
        }
    }
}