package com.nyco.tarkhineh

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {

    val otpLiveData = MutableLiveData<OTPResponse>()

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        }catch (ex : Exception){
            Log.d("this is test for sendOTP",ex.message.toString())
        }

    }
}