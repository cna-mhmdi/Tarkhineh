package com.nyco.tarkhineh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import com.nyco.tarkhineh.model.UserProfile

class TarkhinehRepository(private val tarkhinehServices: TarkhinehServices) {

    private val otpLiveData = MutableLiveData<OTPResponse>()
    private val otpErrorLiveData = MutableLiveData<String>()

    val otp: LiveData<OTPResponse> get() = otpLiveData
    val otpError: LiveData<String> get() = otpErrorLiveData

    private val loginLiveData = MutableLiveData<LoginResponse>()
    private val loginErrorLiveData = MutableLiveData<String>()

    val login: LiveData<LoginResponse> get() = loginLiveData
    val loginError: LiveData<String> get() = loginErrorLiveData

    private val userLiveData = MutableLiveData<UserProfile>()
    private val userErrorLiveData = MutableLiveData<String>()

    val users: LiveData<UserProfile> get() = userLiveData
    val userError: LiveData<String> get() = userErrorLiveData

    suspend fun getUsersDetail(accessToken: String){
        try {
            val usersDetail = tarkhinehServices.getUsersDetail(accessToken)
            userLiveData.postValue(usersDetail)
        }catch (ex:Exception){
            userErrorLiveData.postValue("user detail error : ${ex.message}")
        }
    }

    suspend fun sendLoginReq(loginReq: LoginReq) {
        try {
            val login = tarkhinehServices.sendLogin(loginReq)
            loginLiveData.postValue(login)
        } catch (ex: Exception) {
            loginErrorLiveData.postValue("Login Error: ${ex.message}")
        }
    }

    suspend fun sendOtp(phoneNumber: OTPRequest) {
        try {
            val otp = tarkhinehServices.sendOTPCodes(phoneNumber)
            otpLiveData.postValue(otp)
        } catch (ex: Exception) {
            otpErrorLiveData.postValue("OTP Error: ${ex.message}")

        }
    }
}