package com.nyco.tarkhineh.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteFoods")
data class FavoriteFoods(
    @PrimaryKey @ColumnInfo(name = "foodName") val foodName: String,
    @ColumnInfo(name = "foodDiscount") val foodDiscount: String,
    @ColumnInfo(name = "foodPrice") val foodPrice: String,
    @ColumnInfo(name = "foodStar") val foodStar: String,
    @ColumnInfo(name = "foodDesc") val foodDesc: String,
    @ColumnInfo(name = "isFavorite")val isFavorite:Boolean= false
)
