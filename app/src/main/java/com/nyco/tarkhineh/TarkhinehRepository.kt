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

    private val loginLiveData = MutableLiveData<LoginResponse>()
    private val loginErrorLiveData =MutableLiveData<String>()

    val login: LiveData<LoginResponse> get() = loginLiveData
    val loginError: LiveData<String> get() = loginErrorLiveData



    suspend fun sendLoginReq(loginReq: LoginReq) {
        try {
            val login = tarkhinehServices.sendLogin(loginReq)
            loginLiveData.postValue(login)
        }catch (ex:Exception){
            loginErrorLiveData.postValue("error happend ${ex.message}")
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