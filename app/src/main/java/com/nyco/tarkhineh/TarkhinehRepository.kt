package com.nyco.tarkhineh

import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.Response

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {


    suspend fun sendOtp(phoneNumber: OTPRequest): Response<OTPResponse> {
        return tarkhinehServices.sendOTPCodes(phoneNumber)
    }
}