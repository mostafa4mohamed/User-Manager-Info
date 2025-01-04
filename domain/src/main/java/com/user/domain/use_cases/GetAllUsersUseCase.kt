package com.user.domain.use_cases

import com.user.domain.repo.GetAllUsersRepo

class GetAllUsersUseCase(private val repo: GetAllUsersRepo) {
    suspend operator fun invoke() = repo.getAllUsers()
}