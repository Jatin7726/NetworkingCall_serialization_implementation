package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import retrofit2.http.GET


interface MarsApiService {
    @GET("photos")
    //suspend fun getPhotos(): String  << this is for string
    suspend fun getPhotos(): List<MarsPhoto> //<< this is for list of photos(object)

}

