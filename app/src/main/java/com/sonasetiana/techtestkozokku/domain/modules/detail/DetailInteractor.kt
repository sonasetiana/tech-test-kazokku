package com.sonasetiana.techtestkozokku.domain.modules.detail

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(
    private val repository: RemoteRepository,
    private val localRepository: LocalRepository
) : DetailUseCase {
    override suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>> = repository.getUserDetail(userId)

    override fun getUserPost(
        userId: String,
        limit: Int?
    ): Flow<PagingData<UserPostResponse>> = repository.getUserPost(userId, limit)

    override fun checkUser(id: String): Flow<RoomResult<Boolean>> = localRepository.checkUser(id)

    override suspend fun saveUser(id: String) {
        localRepository.saveUser(id)
    }

    override suspend fun deleteUser(id: String) {
        localRepository.deleteUser(id)
    }

    override suspend fun saveFavorite(data: UserPostResponse) = localRepository.saveFavorite(data)

    override suspend fun deleteFavorite(data: UserPostResponse) = localRepository.deleteFavorite(data)

    override suspend fun saveOwner(data: UserPostResponse) = localRepository.saveOwner(data)

    override suspend fun deleteOwner(data: UserPostResponse) = localRepository.deleteOwner(data)

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = localRepository.checkFavorite(id)
}