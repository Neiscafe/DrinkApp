package com.example.carapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carapp.database.dao.UserDao
import com.example.carapp.model.User


@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object factory {

        private lateinit var db: UserDatabase

        fun getInstance(context: Context): UserDatabase {

            if (::db.isInitialized) return db
            db = Room.databaseBuilder(context, UserDatabase::class.java, "UserDatabase.db")
                .build()
            return db
        }
    }
}