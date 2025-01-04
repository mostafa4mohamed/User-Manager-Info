package com.user.info.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import com.user.data.local.UserManagerDao
import com.user.data.room.base.RoomManger

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {


    @Singleton
    @Provides
    fun provideMainDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RoomManger::class.java,
        "User Manager Info"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideUserManagerDao(db: RoomManger): UserManagerDao = db.userInoManagerDao()
}