package com.codingurkan.tsoft.service

import com.codingurkan.tsoft.model.PhotoResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun photoRequest(
        @Query("q") query: String?,
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int? = 10,
    ): Response<PhotoResponseModel>
}