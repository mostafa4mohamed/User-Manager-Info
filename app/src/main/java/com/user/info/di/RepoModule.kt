package com.user.info.di

import com.user.data.impl.AddUserRepoImpl
import com.user.data.impl.GetAllUsersRepoImpl
import com.user.data.impl.UserDetailsRepoImpl
import com.user.data.local.UserManagerDao
import com.user.domain.repo.AddUserRepo
import com.user.domain.repo.GetAllUsersRepo
import com.user.domain.repo.GetUserDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideGetAllUsersRepo(dao: UserManagerDao): GetAllUsersRepo =
        GetAllUsersRepoImpl(dao)

@Provides
    fun provideAddUserRepo(dao: UserManagerDao): AddUserRepo =
    AddUserRepoImpl(dao)

@Provides
    fun provideGetUserDetailsRepo(dao: UserManagerDao): GetUserDetailsRepo =
    UserDetailsRepoImpl(dao)


}