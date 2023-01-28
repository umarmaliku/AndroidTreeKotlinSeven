package com.example.androidtreekotlinseven.data.repositories

import com.example.androidtreekotlinseven.data.local.room.dao.PhotoDao
import com.example.androidtreekotlinseven.data.models.Photo
import com.example.androidtreekotlinseven.data.remote.api.PostsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class PhotoRepository @Inject constructor(
    private val apiService: PostsApiService,
    private val postDao: PhotoDao
) {
    fun getPhoto(
        onSuccess: (photoList: List<Photo>) -> Unit,
        onFailure: (massage: String) -> Unit
    ) {

        apiService.getPhoto().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postDao.insertPhotos(it)
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                t.localizedMessage?.let {
                    onFailure(it)
                }
            }
        })
    }

    fun getLocalPhotos() = postDao.getAllPhoto()
}