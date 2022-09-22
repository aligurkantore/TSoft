package com.codingurkan.tsoft.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouritePhoto::class],
    version = 1
)
abstract class FavouritePhotoDatabase : RoomDatabase() {
    abstract fun getFavouritePhotoDao(): FavouritePhotoDao
}