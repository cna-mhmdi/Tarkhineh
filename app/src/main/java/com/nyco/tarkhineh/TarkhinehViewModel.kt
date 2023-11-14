package com.nyco.tarkhineh

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarkhinehViewModel(private val tarkhinehRepository: TarkhinehRepository) : ViewModel() {

    val otp: LiveData<OTPResponse> get() = tarkhinehRepository.otp
    fun getOtpError(): LiveData<String> = tarkhinehRepository.otpError

    val login: LiveData<LoginResponse> get() = tarkhinehRepository.login
    fun getLoginError(): LiveData<String> = tarkhinehRepository.loginError

    fun sendLogin(loginReq: LoginReq) {
        viewModelScope.launch(Dispatchers.IO) {
            tarkhinehRepository.sendLoginReq(loginReq)
        }
    }

    fun sendOTPCode(phoneNumber: OTPRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            tarkhinehRepository.sendOtp(phoneNumber)
        }
    }
}