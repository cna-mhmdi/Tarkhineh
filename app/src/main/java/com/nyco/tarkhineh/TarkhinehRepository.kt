package com.nyco.tarkhineh

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices

import com.nyco.tarkhineh.model.OTPResponse

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {


//    private val otpLiveData = MutableLiveData<OTPResponse>()
//    private val otpErrorLiveData = MutableLiveData<String>()
//
//    val otpCode: LiveData<OTPResponse> get() = otpLiveData
//    val otpError: LiveData<String> get() = otpErrorLiveData

    suspend fun sendOtp(phoneNumber: String):OTPResponse {
        return tarkhinehServices.sendOTPCodes(phoneNumber)
    }
}