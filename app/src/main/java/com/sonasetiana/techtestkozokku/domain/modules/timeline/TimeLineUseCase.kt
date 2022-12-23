package com.sonasetiana.techtestkozokku.domain.modules.timeline

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import kotlinx.coroutines.flow.Flow

interface TimeLineUseCase {
    fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>>
    fun getPostByTags(tagName: String, limit: Int?): Flow<PagingData<UserPostResponse>>
}