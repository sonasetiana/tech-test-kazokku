package com.sonasetiana.techtestkozokku.domain.modules.timeline

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class TimeLineInteractor(
    private val repository: RemoteRepository,
    private val localRepository: LocalRepository,
) : TimeLineUseCase {

    override fun getAllPost(limit: Int?): Flow<PagingData<UserPostResponse>> = repository.getAllPost(limit)

    override fun getPostByTags(
        tagName: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = repository.getPostByTags(tagName, limit)

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = localRepository.checkFavorite(id)

    override suspend fun saveFavorite(data: UserPostResponse) = localRepository.saveFavorite(data)

    override suspend fun deleteFavorite(data: UserPostResponse) = localRepository.deleteFavorite(data)

    override suspend fun saveOwner(data: UserPostResponse) = localRepository.saveOwner(data)

    override suspend fun deleteOwner(data: UserPostResponse) = localRepository.deleteOwner(data)
}