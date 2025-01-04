package com.user.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.user.domain.entities.User

@Entity
data class UserRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val name: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val jobTitle: String,
    @ColumnInfo val gender: Int
) {
    companion object {

        fun UserRoomEntity.toUser(): User {
            return User(
                id = id,
                name = name,
                age = age,
                jobTitle = jobTitle,
                gender = gender
            )
        }

        fun User.toUserRoomEntity(): UserRoomEntity {
            return UserRoomEntity(
                name = name,
                age = age,
                jobTitle = jobTitle,
                gender = gender
            )
        }

    }
}