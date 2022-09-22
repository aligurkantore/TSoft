package com.codingurkan.tsoft.model

data class PhotoResponseModel(
    val hits: ArrayList<Hit>,
    val total: Int,
    val totalHits: Int
)