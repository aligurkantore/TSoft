package com.codingurkan.tsoft.ui.fragments.photolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingurkan.tsoft.db.FavouritePhoto
import com.codingurkan.tsoft.model.Hit
import com.codingurkan.tsoft.repository.PhotoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(private val repository: PhotoListRepository) :
    ViewModel() {

    val photoList = MutableLiveData<ArrayList<Hit>>()
    private var job: Job? = null
    private var errorMessage = MutableLiveData<String?>().also { it.value = null }

    fun addToFavourite(photo: Hit) {
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavourite(FavouritePhoto(photo.id))
        }
    }

    suspend fun checkPhoto(id: Int) = repository.checkPhoto(id)

    fun removeFromFavourite(id: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromFavourite(id)
        }
    }

    fun downloadPhotos(page: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.photoListRequest(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { _data ->
                        photoList.postValue(_data.hits)
                    }
                } else {
                    errorMessage.postValue(response.message())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}