package com.nyco.tarkhineh.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class OTPRequest(val phoneNumber: String?)

data class OTPResponse(
    val id: Int,
    val phoneNumber: String,
    val code: String,
)

data class LoginReq(
    val phone_number: String,
    val OTPcode: String
)

data class LoginResponse(
    val message : String ="",
    val access_token : String ="",
    val refresh_token : String = "",
)
