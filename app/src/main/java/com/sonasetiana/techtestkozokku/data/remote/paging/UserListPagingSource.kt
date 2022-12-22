package com.sonasetiana.techtestkozokku.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiServices

class UserListPagingSource(
    private val service: ApiServices,
    private val limit: Int
) : PagingSource<Int, UserResponse>() {

    private companion object {
        const val INITIAL_PAGE = 0
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        val page = params.key ?: INITIAL_PAGE

        return try {
            val response = service.getUserList(page = page, limit = limit)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        }catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}