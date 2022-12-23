package com.sonasetiana.techtestkozokku.domain.modules.timeline

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class TimeLineInteractor(
    private val repository: RemoteRepository
) : TimeLineUseCase {
    override fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>> = repository.getAllPost(limit)

    override fun getPostByTags(
        tagName: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = repository.getPostByTags(tagName, limit)

}