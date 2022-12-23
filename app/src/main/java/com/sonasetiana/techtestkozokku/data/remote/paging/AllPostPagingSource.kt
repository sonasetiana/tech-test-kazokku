package com.sonasetiana.techtestkozokku.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.data.remote.network.ApiServices

class AllPostPagingSource(
    private val service: ApiServices,
    private val limit: Int
) : PagingSource<Int, UserPostResponse>(){
    companion object {
        const val INITIAL_PAGE = 0
    }
    override fun getRefreshKey(state: PagingState<Int, UserPostResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserPostResponse> {
        val page = params.key ?: INITIAL_PAGE
        return try {
            val response = service.getAllPost(page = page, limit = limit)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}