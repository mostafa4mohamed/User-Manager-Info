package com.user.data.impl

import com.user.data.local.UserManagerDao
import com.user.data.room.entity.UserRoomEntity.Companion.toUser
import com.user.domain.entities.User
import com.user.domain.repo.GetAllUsersRepo

class GetAllUsersRepoImpl(private val dao: UserManagerDao) : GetAllUsersRepo {

    override suspend fun getAllUsers(): List<User> {
        return dao.allUsers().map { it.toUser() }
    }
}