package com.nyco.tarkhineh.api

import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import com.nyco.tarkhineh.model.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TarkhinehServices {

    @POST("accounts/api/SendOTPcodes/")
    suspend fun sendOTPCodes(@Body phoneNumber: OTPRequest): OTPResponse

    @POST("accounts/api/users/")
    suspend fun sendLogin(@Body loginReq: LoginReq): LoginResponse

    @GET("accounts/api/users/detail/")
    suspend fun getUsersDetail(@Header("Authorization") accessToken: String): UserProfile

}