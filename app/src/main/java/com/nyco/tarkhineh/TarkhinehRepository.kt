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
    private val otpLiveDataError = MutableLiveData<String>()

    val otp : LiveData<OTPResponse> get() = otpLiveData
    val otpError: LiveData<String> get() = otpLiveDataError

    private val loginLiveData = MutableLiveData<Response<LoginResponse>>()
    private val loginLiveDataError = MutableLiveData<String>()

    val login : LiveData<Response<LoginResponse>> get() = loginLiveData
    val loginError: LiveData<String> get() = loginLiveDataError

    suspend fun sendLoginReq(loginReq: LoginReq): Response<LoginResponse> {
        val login = tarkhinehServices.sendLogin(loginReq)
        loginLiveData.postValue(login)
           return login
    }

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        } catch (ex: Exception) {
            otpLiveDataError.postValue("An Error occurred : ${ex.message}")
        }
    }
}