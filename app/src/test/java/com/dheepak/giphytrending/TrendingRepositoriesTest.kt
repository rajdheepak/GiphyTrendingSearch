package com.dheepak.giphytrending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.dheepak.giphytrending.domain.NetworkService
import com.dheepak.giphytrending.domain.TrendingRepository
import com.dheepak.giphytrending.domain.TrendingRepositoryImpl
import com.dheepak.giphytrending.mockData.dummyResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class TrendingRepositoriesTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    var api: NetworkService = mockk()

    lateinit var trendingRepository: TrendingRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        trendingRepository = TrendingRepositoryImpl(api)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `Did paging source load - For Api success response`() = runBlockingTest {
        coEvery { api.getTrendingGifs(any()) }.returns(Response.success(dummyResponse))
        val expectedSuccess = PagingSource.LoadResult.Page(
            data = dummyResponse.data,
            prevKey = null,
            nextKey = dummyResponse.pagination?.count!!
        )
        assertEquals(
            expectedSuccess, trendingRepository.getTrendingGifs().load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

}