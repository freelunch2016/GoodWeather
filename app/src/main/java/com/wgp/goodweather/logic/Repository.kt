package com.wgp.goodweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wgp.goodweather.logic.model.Place
import com.wgp.goodweather.logic.network.GoodWeatherNetWork
import kotlinx.coroutines.Dispatchers


/**
 * 作为仓库层的统一访问入口
 */
object Repository {

    fun searchPlaces(query:String) = liveData<Result<List<Place>>> {
       val  result = try {
            val placeResponse = GoodWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}