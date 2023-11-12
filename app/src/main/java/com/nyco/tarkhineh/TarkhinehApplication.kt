package com.nyco.tarkhineh

import android.app.Application
import com.nyco.tarkhineh.api.TarkhinehServices
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TarkhinehApplication : Application() {

    lateinit var tarkhinehRepository: TarkhinehRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://tarkhineh-project.iran.liara.run/tarkhineh/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val tarkhinehService = retrofit.create(TarkhinehServices::class.java)

        tarkhinehRepository = TarkhinehRepository(tarkhinehService)
    }
}