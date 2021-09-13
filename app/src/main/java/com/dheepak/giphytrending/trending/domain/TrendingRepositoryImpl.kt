package com.dheepak.giphytrending.trending.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.dheepak.giphytrending.common.db.GiphyDao
import com.dheepak.giphytrending.common.model.DataItem

class TrendingRepositoryImpl(private val networkService: NetworkService, private val giphyDao: GiphyDao): TrendingRepository {

    override fun getTrendingGifs(): PagingSource<Int, DataItem> {
        return object : PagingSource<Int, DataItem>() {

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
                return try {
                    val currentLoadingPageKey = params.key ?: 0
                    val response = networkService.getTrendingGifs(currentLoadingPageKey)

                    if(response.isSuccessful) {
                        val responseData: List<DataItem> = response.body()?.data ?: emptyList()

                        val prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey - 25

                        LoadResult.Page(
                            data = responseData,
                            prevKey = prevKey,
                            nextKey = currentLoadingPageKey.plus(response.body()?.pagination?.count!!)
                        )
                    } else {
                        LoadResult.Error(java.lang.RuntimeException(response.code().toString()))
                    }
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

        }
    }

    override fun getSearchGifs(s: String): PagingSource<Int, DataItem> {
        return object : PagingSource<Int, DataItem>() {

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
                return try {
                    val currentLoadingPageKey = params.key ?: 0
                    val response = networkService.getSearchGifs(s)

                    if(response.isSuccessful) {
                        val responseData: List<DataItem> = response.body()?.data ?: emptyList()

                        val prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey - 25

                        LoadResult.Page(
                            data = responseData,
                            prevKey = prevKey,
                            nextKey = currentLoadingPageKey.plus(response.body()?.pagination?.count!!)
                        )
                    } else {
                        LoadResult.Error(java.lang.RuntimeException(response.code().toString()))
                    }
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

        }
    }

    override fun addToFavorites(dataItem: DataItem) {
        giphyDao.addFavorite(dataItem)
    }

    override fun getFavorites(): LiveData<List<DataItem>> {
        return giphyDao.getFavoritesGifsAsLiveData()
    }

    override fun getFavoritesList(): List<DataItem> {
        return giphyDao.getFavoritesGifsData()
    }

    override fun deleteFromFavorites(id: String) {
        giphyDao.deleteFavorite(id)
    }
}