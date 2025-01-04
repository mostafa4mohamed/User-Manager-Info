package com.user.domain.use_cases

import com.user.domain.entities.User
import com.user.domain.repo.AddUserRepo

class AddUserUseCase(private val repo: AddUserRepo) {

    suspend operator fun invoke(user: User) =
        repo.addUsers(user)
}