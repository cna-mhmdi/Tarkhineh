package com.nyco.tarkhineh

import android.content.Context
import android.content.SharedPreferences

object TokenManager {

    fun saveState(context: Context, key: String, condition: Boolean) {
        val editor = getSPStartingProcess(context).edit()
        editor.putBoolean(key, condition)
        editor.apply()
    }

    fun getState(context: Context, key: String): Boolean {
        return getSPStartingProcess(context).getBoolean(key, false)
    }

    private fun getSPStartingProcess(context: Context): SharedPreferences {
        return context.getSharedPreferences("startingProcess", Context.MODE_PRIVATE)
    }

}