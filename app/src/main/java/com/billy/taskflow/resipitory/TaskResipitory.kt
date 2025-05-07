package com.billy.taskflow.resipitory

import android.annotation.SuppressLint
import com.billy.taskflow.data.UserDao
import com.google.firebase.firestore.auth.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(@SuppressLint("RestrictedApi") user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}