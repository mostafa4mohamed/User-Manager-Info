package com.user.data.impl

import com.user.data.local.UserManagerDao
import com.user.data.room.entity.UserRoomEntity.Companion.toUserRoomEntity
import com.user.domain.entities.User
import com.user.domain.repo.AddUserRepo

class AddUserRepoImpl(private val dao:UserManagerDao):AddUserRepo{

    override suspend fun addUsers(user: User) {
        dao.addUser(user.toUserRoomEntity())
    }

}