package com.nyco.tarkhineh.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteFoods: FavoriteFoods)

    @Delete
    suspend fun delete(favoriteFoods: FavoriteFoods)

    @Query("SELECT * FROM favoriteFoods")
    fun getAllFavoriteFoods(): LiveData<List<FavoriteFoods>>

}