package com.nyco.tarkhineh.api

import android.content.Context
import android.content.SharedPreferences

object TokenManager {

    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"

    fun saveState(context: Context, key:String, condition: Boolean){
        val editor = getSPStartingProcess(context).edit()
        editor.putBoolean(key,condition)
        editor.apply()
    }

    fun getState(context: Context,key: String):Boolean{
        return getSPStartingProcess(context).getBoolean(key,false)
    }

    private fun getSPStartingProcess(context: Context):SharedPreferences {
        return context.getSharedPreferences("startingProcess",Context.MODE_PRIVATE)
    }

//    fun saveAccessToken(context: Context, accessToken: String) {
//        val editor = getSharedPreferences(context).edit()
//        editor.putString(ACCESS_TOKEN_KEY, accessToken)
//        editor.apply()
//    }
//
//    fun saveRefreshToken(context: Context, refreshToken: String) {
//        val editor = getSharedPreferences(context).edit()
//        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
//        editor.apply()
//    }

    //    fun getAccessToken(context: Context): String? {
//        return getSharedPreferences(context).getString(ACCESS_TOKEN_KEY, null)
//    }
//
//    fun getRefreshToken(context: Context): String? {
//        return getSharedPreferences(context).getString(REFRESH_TOKEN_KEY, null)
//    }
//
//    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
//        val editor = getSharedPreferences(context).edit()
//        editor.putString(ACCESS_TOKEN_KEY, accessToken)
//        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
//        editor.apply()
//    }



    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)
    }
}