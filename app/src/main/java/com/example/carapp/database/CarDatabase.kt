package com.example.carapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.carapp.database.dao.CarDao
import com.example.carapp.model.Car


@Database(
    entities = [Car::class],
    version = 1,
    exportSchema = false
)
abstract class CarDatabase : RoomDatabase(){

    abstract fun getFilmeDao(): CarDao

    companion object factory{

        private lateinit var db: CarDatabase

        fun getInstance(context: Context): CarDatabase{

            if(::db.isInitialized) return db
            db = databaseBuilder(context, CarDatabase::class.java, "CarDatabase.db").build()
            return db
        }
    }

}