package com.user.domain.repo

import com.user.domain.entities.User

interface GetAllUsersRepo {

    suspend fun getAllUsers(): List<User>

}