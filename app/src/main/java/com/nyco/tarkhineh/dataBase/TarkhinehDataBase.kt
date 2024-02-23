package com.nyco.tarkhineh.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteFoods::class], version = 1, exportSchema = false)
abstract class TarkhinehDataBase : RoomDatabase() {

    abstract fun favoriteFoodDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: TarkhinehDataBase? = null

        fun getDataBase(context: Context): TarkhinehDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarkhinehDataBase::class.java,
                    "tarkhineh-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}