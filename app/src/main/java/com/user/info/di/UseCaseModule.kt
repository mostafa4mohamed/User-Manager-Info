package com.user.info.di

import com.user.domain.repo.AddUserRepo
import com.user.domain.repo.GetAllUsersRepo
import com.user.domain.repo.GetUserDetailsRepo
import com.user.domain.use_cases.AddUserUseCase
import com.user.domain.use_cases.GetAllUsersUseCase
import com.user.domain.use_cases.UserDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllUsersUseCase(repo: GetAllUsersRepo) =
        GetAllUsersUseCase(repo)

    @Provides
    fun provideUserDetailsUseCase(repo: GetUserDetailsRepo) =
        UserDetailsUseCase(repo)

    @Provides
    fun provideAddUserUseCase(repo: AddUserRepo) =
        AddUserUseCase(repo)
}