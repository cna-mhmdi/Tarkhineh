package com.nyco.tarkhineh.api

import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TarkhinehServices {

    @POST("accounts/api/SendOTPcodes/")
    suspend fun sendOTPCodes(@Body phoneNumber: OTPRequest): OTPResponse

    @POST("accounts/api/users/")
    suspend fun sendLogin(@Body loginReq: LoginReq): LoginResponse

}