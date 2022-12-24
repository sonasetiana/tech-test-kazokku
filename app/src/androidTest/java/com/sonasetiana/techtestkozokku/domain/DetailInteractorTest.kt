package com.sonasetiana.techtestkozokku.domain

import androidx.paging.PagingData
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiResponse
import com.sonasetiana.techtestkozokku.domain.modules.detail.DetailUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class DetailInteractorTest: DetailUseCase {
    override suspend fun getUserDetail(userId: String): Flow<ApiResponse<UserDetailResponse>> = flow {
        delay(1000)
        emit(ApiResponse.Success(DummyData.dataUserDetail))
    }

    override fun getUserPost(userId: String, limit: Int?): Flow<PagingData<UserPostResponse>> = flow {
        delay(1000)
        emit(PagingData.from(DummyData.userPostItems))
    }

    override fun checkUser(id: String): Flow<RoomResult<Boolean>> = flowOf(RoomResult.Success(false))

    override suspend fun saveUser(id: String) {
        
    }

    override suspend fun deleteUser(id: String) {
        
    }

    override suspend fun saveFavorite(data: UserPostResponse) {
        
    }

    override suspend fun deleteFavorite(data: UserPostResponse) {
        
    }

    override suspend fun saveOwner(data: UserPostResponse) {
        
    }

    override suspend fun deleteOwner(data: UserPostResponse) {
        
    }

    override fun checkFavorite(id: String): Flow<RoomResult<Boolean>> = flowOf(RoomResult.Success(false))
}