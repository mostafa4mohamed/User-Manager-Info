package com.user.data.impl

import com.user.data.local.UserManagerDao
import com.user.data.room.entity.UserRoomEntity.Companion.toUser
import com.user.domain.entities.User
import com.user.domain.repo.GetUserDetailsRepo

class UserDetailsRepoImpl(private val dao: UserManagerDao):GetUserDetailsRepo{

    override suspend fun getUserDetails(userId: Int): User {
        return dao.userDetails(userId).toUser()
    }
}