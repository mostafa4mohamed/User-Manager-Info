package com.user.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.data.room.entity.UserRoomEntity

@Dao
interface UserManagerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserRoomEntity)

    @Query("SELECT * FROM UserRoomEntity WHERE id == :id")
    suspend fun userDetails(id: Int): UserRoomEntity


    @Query("SELECT * FROM UserRoomEntity")
    suspend fun allUsers(): List<UserRoomEntity>

}