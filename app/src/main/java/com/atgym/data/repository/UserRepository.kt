package com.atgym.data.repository

import com.atgym.data.database.dao.UserProfileDao
import com.atgym.data.database.entity.UserProfile
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userProfileDao: UserProfileDao) {
    val profile: Flow<UserProfile?> = userProfileDao.getProfile()
    suspend fun updateProfile(profile: UserProfile) = userProfileDao.upsert(profile)
}
