package com.example.githubuserapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface likeDAO {
    @Insert
    suspend fun addToFavorite(interestUsers: InterestUsers)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<InterestUsers>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeFromFavorite(id: Int): Int

}
