package com.dheepak.giphytrending.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dheepak.giphytrending.common.model.DataItem

@Database(entities = [DataItem::class], version = 1)
abstract class GiphyDb: RoomDatabase() {
    abstract val giphyDao: GiphyDao
}