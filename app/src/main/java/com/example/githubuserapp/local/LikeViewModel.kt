package com.example.githubuserapp.local

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class LikeViewModel(application: Application): AndroidViewModel(application) {
    private var userDao: likeDAO?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<InterestUsers>>?{
        return userDao?.getFavoriteUser()
    }
}