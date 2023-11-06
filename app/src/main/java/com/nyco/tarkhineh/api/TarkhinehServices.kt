package com.nyco.tarkhineh.api

import com.nyco.tarkhineh.model.OTPRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TarkhinehServices {

    @POST("api/SendOTPcodes/")
    suspend fun sendOTPCodes(@Body phoneNumber: OTPRequest): Response<OTPRequest>

}