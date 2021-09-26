package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.User
import com.example.shoppingapp.db.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository@Inject constructor(
    val userDao: UserDao
) {
    suspend fun insert(user : User) = userDao.insert(user)

    fun userInformation() : Flow<User> = userDao.userInfo()
}