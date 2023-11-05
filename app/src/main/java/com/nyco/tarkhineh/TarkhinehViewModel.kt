package com.nyco.tarkhineh

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyco.tarkhineh.model.OTPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarkhinehViewModel(private val tarkhinehRepository: TarkhinehRepository):ViewModel() {


    fun sendOTPCode(phoneNumber: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = tarkhinehRepository.sendOtp(phoneNumber)

            } catch (e: Exception) {
                // Handle the exception, e.g., show an error message
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}