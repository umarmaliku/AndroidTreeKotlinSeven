package com.example.androidtreekotlinseven.di

import android.content.Context
import androidx.room.Room
import com.example.androidtreekotlinseven.data.local.room.PhotoDatabase
import com.example.androidtreekotlinseven.data.local.room.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePhotoDatabase(@ApplicationContext context : Context): PhotoDatabase {
        return Room.databaseBuilder(
            context,
            PhotoDatabase::class.java,
            "image-name"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePhotoDao(database: PhotoDatabase): PhotoDao {
        return database.photoDao()
    }
}