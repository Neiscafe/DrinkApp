package com.example.carapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.carapp.database.dao.DrinkDao
import com.example.carapp.model.Drink


@Database(
    entities = [Drink::class],
    version = 1,
    exportSchema = false
)
abstract class DrinkDatabase : RoomDatabase(){

    abstract fun getFilmeDao(): DrinkDao

    companion object factory{

        private lateinit var db: DrinkDatabase

        fun getInstance(context: Context): DrinkDatabase{

            if(::db.isInitialized) return db
            db = databaseBuilder(context, DrinkDatabase::class.java, "CarDatabase.db").build()
            return db
        }
    }

}