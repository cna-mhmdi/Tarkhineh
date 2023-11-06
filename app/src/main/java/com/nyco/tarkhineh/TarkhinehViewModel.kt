package com.nyco.tarkhineh

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TarkhinehViewModel(private val tarkhinehRepository: TarkhinehRepository):ViewModel() {

    fun sendOTPCode(phoneNumber: OTPRequest, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = tarkhinehRepository.sendOtp(phoneNumber)
                if (response.isSuccessful) {
                    val responseData = response.body()
                    withContext(Dispatchers.Main) {
                        // Update UI with the response data (if needed)
                        Toast.makeText(
                            context,
                            "Response is : $responseData",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // Show error message or handle the error UI (if needed)
                        Toast.makeText(
                            context,
                            "Error: ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
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
}