package com.example.carapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.carapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = IGNORE)
    suspend fun save(user: User)

    @Query("SELECT EXISTS(SELECT 1 FROM User where user = :userName AND password = :password)")
    fun checkIfExists(userName: String, password: String): LiveData<Boolean>

}
