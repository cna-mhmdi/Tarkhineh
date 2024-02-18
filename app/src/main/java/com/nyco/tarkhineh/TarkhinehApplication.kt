package com.nyco.tarkhineh

import android.app.Application
import androidx.room.Room
import com.nyco.tarkhineh.api.TarkhinehServices
import com.nyco.tarkhineh.dataBase.TarkhinehDataBase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TarkhinehApplication : Application() {

    lateinit var tarkhinehRepository: TarkhinehRepository
    lateinit var tarkhinehDataBase: TarkhinehDataBase

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://tarkhineh--project.iran.liara.run/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val tarkhinehService = retrofit.create(TarkhinehServices::class.java)

        tarkhinehDataBase = TarkhinehDataBase.getDataBase(applicationContext)

        tarkhinehRepository = TarkhinehRepository(tarkhinehService,tarkhinehDataBase.favoriteFoodDao())
    }
}