package com.codingurkan.tsoft.service

import android.content.Context
import com.codingurkan.tsoft.db.FavouritePhotoDatabase
import com.codingurkan.tsoft.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private var retrofit: ApiService? = null

    @Provides
    @Singleton
    fun getClient(): ApiService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        return retrofit as ApiService
    }


    @Provides
    @Singleton
    fun provideFavPhotoDatabase(
        @ApplicationContext app: Context,
    ) = androidx.room.Room.databaseBuilder(app, FavouritePhotoDatabase::class.java, "photo_db")
        .build()

    @Provides
    @Singleton
    fun provideFavPhotoDao(db: FavouritePhotoDatabase) = db.getFavouritePhotoDao()

}