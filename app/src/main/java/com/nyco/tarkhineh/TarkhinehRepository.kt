package com.nyco.tarkhineh

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {

    private val otpLiveData = MutableLiveData<OTPResponse>()
    private val otpLiveDataError = MutableLiveData<String>()

    val otp : LiveData<OTPResponse> get() = otpLiveData
    val otpError: LiveData<String> get() = otpLiveDataError

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        } catch (ex: Exception) {
            otpLiveDataError.postValue("An Error occurred : ${ex.message}")
        }
    }
}