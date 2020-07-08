package com.wgp.goodweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgp.goodweather.logic.Repository
import com.wgp.goodweather.logic.model.Place


class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    //存放界面中的地址数据
    val placeList = ArrayList<Place>()


    //提供给Activity或Fragment中进行被监听的LiveData
    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()

}