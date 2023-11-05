package com.nyco.tarkhineh.api

import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TarkhinehServices {

    @POST("tarkhineh/api/SendOTPcodes")
    suspend fun sendOTPCodes(@Body phoneNumber: String): OTPResponse

}