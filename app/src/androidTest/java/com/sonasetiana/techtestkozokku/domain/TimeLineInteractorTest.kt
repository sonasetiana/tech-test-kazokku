package com.sonasetiana.techtestkozokku.domain

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

open class TimeLineInteractorTest : TimeLineUseCase {
    override fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>> = flow {
        delay(1000)
        emit(PagingData.from(DummyData.postItems))
    }

    override fun getPostByTags(tagName: String, limit: Int?): Flow<PagingData<UserPostResponse>> = flowOf(
        PagingData.from(
            DummyData.postItems.filter { it.tags.contains(tagName) }
        )
    )

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = flowOf(RoomResult.Success(false))

    override suspend fun saveFavorite(data: UserPostResponse) {
        
    }

    override suspend fun deleteFavorite(data: UserPostResponse) {
        
    }

    override suspend fun saveOwner(data: UserPostResponse) {
        
    }

    override suspend fun deleteOwner(data: UserPostResponse) {
        
    }
}