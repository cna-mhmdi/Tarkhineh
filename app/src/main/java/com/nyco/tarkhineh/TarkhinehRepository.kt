package com.nyco.tarkhineh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import retrofit2.Response

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {

    private val otpLiveData = MutableLiveData<OTPResponse>()
    val otp: LiveData<OTPResponse> get() = otpLiveData

    private val loginLiveData = MutableLiveData<Response<LoginResponse>>()
    val login: LiveData<Response<LoginResponse>> get() = loginLiveData


    suspend fun sendLoginReq(loginReq: LoginReq): Response<LoginResponse> {
        val login = tarkhinehServices.sendLogin(loginReq)
        loginLiveData.postValue(login)
        return login
    }

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        } catch (_: Exception) {

        }
    }
}