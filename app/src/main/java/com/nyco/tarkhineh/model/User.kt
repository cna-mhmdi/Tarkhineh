package com.nyco.tarkhineh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class OTPRequest(val phoneNumber: String)

data class OTPResponse(
    val id: Int = 0 ,
    val phoneNumber: String = "",
    val code: String = "",
)
