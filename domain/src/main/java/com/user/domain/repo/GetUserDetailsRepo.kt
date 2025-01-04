package com.user.domain.repo

import com.user.domain.entities.User

interface GetUserDetailsRepo {

    suspend fun getUserDetails(userId:Int):User?
}