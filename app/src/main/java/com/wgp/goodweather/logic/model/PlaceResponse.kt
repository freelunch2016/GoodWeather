package com.wgp.goodweather.logic.model

import android.location.Address
import com.google.gson.annotations.SerializedName


/**
 * 请求地址位置
 * 状态，及位置
 */
data class PlaceResponse(val status: String, val places: List<Place>)
data class Place(
    //区域代码
    val id:String,
    //地址名称
    val name: String,
    //经纬度
    val location: Location,
    //详细地址，使用@SerializedName注解方式，是来让Json字段和Kotlin字段之间建立映射关系
    @SerializedName("formatted_address") val address: String,
    //详细位置的区域代码
    val place_id:String
)

/**
 * 地址的经纬度
 */
data class Location(val lng: String, val lat: String)