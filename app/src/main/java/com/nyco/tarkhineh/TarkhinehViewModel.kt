package com.nyco.tarkhineh

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyco.tarkhineh.dataBase.FavoriteFoods
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.LoginResponse
import com.nyco.tarkhineh.model.OTPRequest
import com.nyco.tarkhineh.model.OTPResponse
import com.nyco.tarkhineh.model.UpdateUser
import com.nyco.tarkhineh.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarkhinehViewModel(private val tarkhinehRepository: TarkhinehRepository) : ViewModel() {

    /**
     * this section is for configuration of dataBase
     */

    val allFavoriteFoods: LiveData<List<FavoriteFoods>> = tarkhinehRepository.allFavoriteFoods

    fun insertFav(favoriteFoods: FavoriteFoods) = viewModelScope.launch {
        tarkhinehRepository.insertFav(favoriteFoods)
    }

    fun deleteFav(favoriteFoods: FavoriteFoods) = viewModelScope.launch {
        tarkhinehRepository.deleteFav(favoriteFoods)
    }

    /**
     * end of the DB configuration
     */

    val otp: LiveData<OTPResponse> get() = tarkhinehRepository.otp
    fun getOtpError(): LiveData<String> = tarkhinehRepository.otpError

    val login: LiveData<LoginResponse> get() = tarkhinehRepository.login
    fun getLoginError(): LiveData<String> = tarkhinehRepository.loginError

    val users: LiveData<UserProfile> get() = tarkhinehRepository.users
    fun getUsersError(): LiveData<String> = tarkhinehRepository.userError

    val updateUser: LiveData<UpdateUser> get() = tarkhinehRepository.updateUser
    fun getUpdateUserError(): LiveData<String> = tarkhinehRepository.updateUserError

    fun updateUserDetail(accessToken: String, user: UpdateUser) {
        viewModelScope.launch(Dispatchers.IO) {
            tarkhinehRepository.updateUserDetail(accessToken, user)
        }
    }

    fun getUsersDetail(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tarkhinehRepository.getUsersDetail(accessToken)
        }
    }

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