package com.codingurkan.tsoft.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouritePhotoDao {
    @Insert
    suspend fun addToFavourite(favouritePhoto: FavouritePhoto)

    @Query("SELECT count(*) FROM favourite_photo WHERE favourite_photo.id_photo = :id")
    suspend fun checkPhoto(id: String): Int

    @Query("DELETE FROM favourite_photo WHERE favourite_photo.id_photo = :id")
    suspend fun removeFromFavourite(id: String): Int


}