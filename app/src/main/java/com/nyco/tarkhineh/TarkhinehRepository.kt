package com.nyco.tarkhineh

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.Call
import retrofit2.Response

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {

    private val otpLiveData = MutableLiveData<OTPResponse>()
    val otp : LiveData<OTPResponse> get() = otpLiveData

    private val loginLiveData = MutableLiveData<LoginResponse>()
    val login : LiveData<LoginResponse> get() = loginLiveData


    suspend fun sendLoginReq(loginReq: LoginReq) {
        try {
            val login = tarkhinehServices.sendLogin(loginReq)
            loginLiveData.postValue(login)
        } catch (_: Exception) {

        }
    }

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        } catch (_: Exception) {

        }
    }
}