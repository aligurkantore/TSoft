package com.codingurkan.tsoft.repository

import com.codingurkan.tsoft.db.FavouritePhoto
import com.codingurkan.tsoft.db.FavouritePhotoDao
import com.codingurkan.tsoft.service.ApiService
import com.codingurkan.tsoft.util.API_KEY
import javax.inject.Inject

class PhotoSearchRepository @Inject constructor(
    private val apiService: ApiService,
    private val favouritePhotoDao: FavouritePhotoDao,
) {

    suspend fun photoListWithSearch(q: String?, page: Int) =
        apiService.photoRequest(q, API_KEY, page)

    suspend fun addToFavourite(favouritePhoto: FavouritePhoto) =
        favouritePhotoDao.addToFavourite(favouritePhoto)

    suspend fun checkPhoto(id: Int) = favouritePhotoDao.checkPhoto(id.toString())

    suspend fun removeFromFavourite(id: Int) {
        favouritePhotoDao.removeFromFavourite(id.toString())
    }
}