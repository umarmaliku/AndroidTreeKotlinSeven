package com.example.androidtreekotlinseven.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtreekotlinseven.data.models.Photo
import com.example.androidtreekotlinseven.data.repositories.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: PhotoRepository) : ViewModel() {

    private val _photoLiveData = MutableLiveData<List<Photo>>()
    val photoLiveData: LiveData<List<Photo>> = _photoLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    var imageLiveData: LiveData<List<Photo>>? = null

    init {
        getPhoto()
        getLocalPhoto()
    }

    private fun getPhoto() {
        return repository.getPhoto(
            onSuccess = {
                _photoLiveData.value = it
            },
            onFailure = {
                _errorLiveData.value = it
            }
        )
    }
    private fun getLocalPhoto() {
        imageLiveData = repository.getLocalPhotos()
    }
}