package com.wgp.goodweather

import android.app.Application
import android.content.Context


class GoodWeatherApplication : Application() {
    companion object{
        lateinit var context: Context
        //彩云天气token
        const val TOKEN ="pIe1zZfAVUuw0zpB"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}