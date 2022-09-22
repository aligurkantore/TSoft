package com.codingurkan.tsoft.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favourite_photo")
data class FavouritePhoto(
    var id_photo : Int
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}