package com.nyco.tarkhineh.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class OTPRequest(val phoneNumber: String?)

data class OTPResponse(
    val id: Int,
    val phoneNumber: String,
    val code: String,
)

data class LoginReq(
    val phone_number: String,
    val OTPcode: String,
)

data class LoginResponse(
    val message: String = "",
    val access_token: String = "",
    val refresh_token: String = "",
)

data class UserProfile(
    val id: Int,
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val is_staff: Boolean?,
    val is_active: Boolean?,
    val date_joined: String?,
    val nick_name: String?,
    val date_birth: String?,
    val username: String?,
    val phone_number: String?,
    val avavtar: String?
)
