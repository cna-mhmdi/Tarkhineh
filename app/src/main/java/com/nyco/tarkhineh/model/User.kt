package com.nyco.tarkhineh.model

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
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val nick_name: String?,
    val date_birth: String?,
    val phone_number: String?,
)

data class SaveDataResponse(
    val message: String,
    val newData: UpdateUser
)

data class UpdateUser(
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val date_birth: String?,
    val nick_name: String?,
)
