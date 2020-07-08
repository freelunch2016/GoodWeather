package com.wgp.goodweather.logic.network

import androidx.annotation.VisibleForTesting
import com.wgp.goodweather.GoodWeatherApplication
import com.wgp.goodweather.logic.model.DailyResponse
import com.wgp.goodweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 请求天气的接口
 */
interface WeatherService {
    /**
     * 请求实时天气
     */
    @GET("v2.5/${GoodWeatherApplication.TOKEN}/{lat},{lng}/realtime.json")
    fun getRealtimeWeather(@Path("lat") lat:String,@Path("lng") lng:String):Call<RealtimeResponse>

    /**
     * 请求当天及未来几天的天气
     * 注意，这里的经纬度参数，是先纬度后经度
     */
    @GET("v2.5/${GoodWeatherApplication.TOKEN}/{lat},{lng}/daily.json")
    fun getDailyWeather(@Path("lat") lat:String,@Path("lng") lng:String):Call<DailyResponse>
}