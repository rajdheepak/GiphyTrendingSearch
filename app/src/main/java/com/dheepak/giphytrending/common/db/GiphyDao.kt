package com.dheepak.giphytrending.common.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dheepak.giphytrending.common.model.DataItem

@Dao
interface GiphyDao {
    @Query("SELECT * from gifTable")
    fun getFavoritesGifs(): PagingSource<Int, DataItem>

    @Query("SELECT * from gifTable")
    fun getFavoritesGifsAsLiveData(): LiveData<List<DataItem>>

    @Query("SELECT * from gifTable")
    fun getFavoritesGifsData(): List<DataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(dataItem: DataItem)

    @Query("DELETE FROM gifTable WHERE id = :id ")
    fun deleteFavorite(id: String)
}