package com.nyco.tarkhineh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MainFood(
    val foodName: String = "",
    val foodDiscount: String = "",
    val foodPrice: String = "",
    val foodStar: String = "",
    val foodDesc: String= "",
    val isFavorite: Boolean = false
):Parcelable

@Parcelize
data class FavoriteFoods(
    val foodName: String = "",
    val foodDiscount: String = "",
    val foodPrice: String = "",
    val foodStar: String = "",
    val foodDesc: String= "",
    val isFavorite: Boolean = false
):Parcelable

@Parcelize
data class MenuFood(
    val foodName: String = "",
    val foodDesc: String = "",
    val foodDiscount: String = "",
    val foodPrice: String = "",
    val foodStar: String = "",
    val isFavorite: Boolean = false
):Parcelable

@Parcelize
data class SearchFood(
    val foodName: String = "",
    val foodCategory: String = ""
):Parcelable