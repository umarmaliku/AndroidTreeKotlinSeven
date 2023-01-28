package com.example.androidtreekotlinseven.data.remote.api

import com.example.androidtreekotlinseven.data.models.Photo
import retrofit2.Call
import retrofit2.http.GET


interface PostsApiService {

    @GET("albums/1/photos")
    fun getPhoto(): Call<List<Photo>>
}