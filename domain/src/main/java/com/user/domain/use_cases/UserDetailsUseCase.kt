package com.user.domain.use_cases

import com.user.domain.repo.GetUserDetailsRepo

class UserDetailsUseCase(private val repo: GetUserDetailsRepo) {
    suspend operator fun invoke(userId: Int) = repo.getUserDetails(userId)
}