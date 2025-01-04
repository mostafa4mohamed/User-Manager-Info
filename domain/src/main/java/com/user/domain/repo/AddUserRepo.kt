package com.user.domain.repo

import com.user.domain.entities.User

interface AddUserRepo {

    suspend fun addUsers(user: User)

}