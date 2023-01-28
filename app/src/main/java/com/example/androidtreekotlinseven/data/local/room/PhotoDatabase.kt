package com.example.androidtreekotlinseven.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtreekotlinseven.data.local.room.dao.PhotoDao
import com.example.androidtreekotlinseven.data.models.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}