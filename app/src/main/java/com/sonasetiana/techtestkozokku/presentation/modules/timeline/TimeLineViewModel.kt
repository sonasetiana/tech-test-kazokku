package com.sonasetiana.techtestkozokku.presentation.modules.timeline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineUseCase
import kotlinx.coroutines.flow.Flow

class TimeLineViewModel(
    private val useCase: TimeLineUseCase
) : ViewModel(){
    var timeLines : Flow<PagingData<UserPostResponse>> = useCase.getAllPost(20).cachedIn(viewModelScope)

    fun setTagName(tagName: String) {
        Log.d("TAGNAME", "setTagName: $tagName")
        timeLines = if (tagName.isEmpty()) {
            useCase.getAllPost(20).cachedIn(viewModelScope)
        } else {
            useCase.getPostByTags(tagName, limit = 20).cachedIn(viewModelScope)
        }
    }
}