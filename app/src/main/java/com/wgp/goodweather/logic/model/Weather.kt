package com.wgp.goodweather.logic.model

/**
 * 封装实时天气和daily天气
 */
data class Weather(val realtime: RealtimeResponse.Realtime,val daily: DailyResponse.Daily) {
}