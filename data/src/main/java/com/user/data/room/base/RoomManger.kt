package com.user.data.room.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.user.data.local.UserManagerDao
import com.user.data.room.entity.UserRoomEntity

@Database(
    entities = [UserRoomEntity::class],
    version = 4,
    exportSchema = false,
)
abstract class RoomManger : RoomDatabase() {

    abstract fun userInoManagerDao(): UserManagerDao

}