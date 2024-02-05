package com.nyco.tarkhineh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ShoppingCard(
    val foodName: String = "",
    val foodDiscount: String = "",
    val foodPrice: String = "",
    val foodStar: String = "",
    val foodDesc: String= ""
):Parcelable
