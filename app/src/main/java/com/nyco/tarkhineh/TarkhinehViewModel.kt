package com.nyco.tarkhineh

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TarkhinehViewModel(private val tarkhinehRepository: TarkhinehRepository) : ViewModel() {

    val otp: LiveData<OTPResponse> get() = tarkhinehRepository.otp
    fun getOTPError(): LiveData<String> = tarkhinehRepository.otpError

    val login: LiveData<Response<LoginResponse>> get() = tarkhinehRepository.login
    fun getLoginError(): LiveData<String> = tarkhinehRepository.loginError

    fun sendLogin(loginReq: LoginReq, context:Context){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = tarkhinehRepository.sendLoginReq(loginReq)

                if (response.isSuccessful) {
                    val responseData = response.body()
                    withContext(Dispatchers.Main) {

                        Toast.makeText(
                            context,
                            "Response is : $responseData",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                } else {
                    withContext(Dispatchers.Main) {
                        
                        Toast.makeText(
                            context,
                            "Error: ${response.errorBody()}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("THISISMESSAGEFOR",response.errorBody().toString())
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    // Handle other exceptions (e.g., network error) here
                    Toast.makeText(
                        context,
                        "Error fail: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun sendOTPCode(phoneNumber: OTPRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            tarkhinehRepository.sendOtp(phoneNumber)
        }
    }
}