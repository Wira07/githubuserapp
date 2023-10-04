package com.example.githubuserapp.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuserapp.api.RetrofitUser
import com.example.githubuserapp.local.InterestUsers
import com.example.githubuserapp.local.UserDatabase
import com.example.githubuserapp.local.likeDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {

    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: likeDAO?
    private var userDb: UserDatabase?

    
    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String){
        _isLoading.value = true
        RetrofitUser.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                        _isLoading.value = false
                    }
                }
                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    _isLoading.value = false
                    t.message?.let { Log.d("Failure", it) }
                }
            })

    }

    fun getUserDetail(): LiveData<DetailUserResponse>{
        return user
    }

    fun addToFavorite(username: String, id: Int, avatar_url: String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = InterestUsers(
                username,
                id,
                avatar_url
            )
            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }

}