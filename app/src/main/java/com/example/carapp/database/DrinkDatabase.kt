package com.example.carapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.carapp.database.dao.DrinkDao
import com.example.carapp.model.DrinkEntity


@Database(
    entities = [DrinkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DrinkDatabase : RoomDatabase(){

    abstract fun getDrinkDao(): DrinkDao

    companion object factory{

        private lateinit var db: DrinkDatabase

        fun getInstance(context: Context): DrinkDatabase{

            if(::db.isInitialized) return db
            db = databaseBuilder(context, DrinkDatabase::class.java, "DrinkDatabase.db").build()
            return db
        }
    }

}