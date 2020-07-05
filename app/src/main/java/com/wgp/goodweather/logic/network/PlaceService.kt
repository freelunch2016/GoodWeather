package com.wgp.goodweather.logic.network

import com.wgp.goodweather.GoodWeatherApplication
import com.wgp.goodweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceService {

    @GET("v2/place?token=${GoodWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String):Call<PlaceResponse>
}

