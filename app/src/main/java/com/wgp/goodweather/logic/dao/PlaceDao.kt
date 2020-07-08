package com.wgp.goodweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.wgp.goodweather.GoodWeatherApplication
import com.wgp.goodweather.logic.model.Place


object PlaceDao {

    private fun sharedPreferences() =
        GoodWeatherApplication.context.getSharedPreferences("good_weather", Context.MODE_PRIVATE)

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }
}



















