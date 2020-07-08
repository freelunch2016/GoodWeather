package com.wgp.goodweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wgp.goodweather.logic.dao.PlaceDao
import com.wgp.goodweather.logic.model.Place
import com.wgp.goodweather.logic.model.Weather
import com.wgp.goodweather.logic.network.GoodWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext


/**
 * 作为仓库层的统一访问入口
 */
object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO){
        val placeResponse = GoodWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok"){
            val places = placeResponse.places
            Result.success(places)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


//        liveData<Result<List<Place>>>(Dispatchers.IO) {
//        val result = try {
//            val placeResponse = GoodWeatherNetWork.searchPlaces(query)
//            if (placeResponse.status == "ok") {
//                val places = placeResponse.places
//                Result.success(places)
//            } else {
//                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
//            }
//
//        } catch (e: Exception) {
//            Result.failure<List<Place>>(e)
//        }
//        emit(result)
//    }

    /**
     * 请求天气
     */
    fun refreshWeather(lat: String, lng: String) = fire(Dispatchers.IO){
        coroutineScope {
           val deferredRealtime =  async {
                GoodWeatherNetWork.getRealtimeWeather(lat, lng)
            }
            val deferredDaily = async { GoodWeatherNetWork.getDailyWeather(lat, lng) }

            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()

            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status} . daily response status is ${dailyResponse.status}"))
            }
        }
    }
        //liveData(Dispatchers.IO) {
//        val result = try {
//            coroutineScope {
//                val deferredRealtime = async {
//                    GoodWeatherNetWork.getRealtimeWeather(lat, lng)
//                }
//
//                val deferredDaily = async {
//                    GoodWeatherNetWork.getDailyWeather(lat, lng)
//                }
//
//                val realtimeResponse = deferredRealtime.await()
//                val dailyResponse = deferredDaily.await()
//
//                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
//                    val weather =
//                        Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
//
//                    Result.success(weather)
//                } else {
//                    Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status} . daily response status is ${dailyResponse.status}"))
//                }
//            }
//
//        } catch (e: Exception) {
//            Result.failure<Weather>(e)
//        }
//
//        emit(result)
//    }



    //自定义方法，统一处理异常问题
    private fun <T>fire(context:CoroutineContext,block:suspend() -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e : Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }

    //这些虽然操作很快，标准写法还是要开启子线程的方式。用LiveData的写法
    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}