package com.wgp.goodweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * retrofit构建器
 * 单例模式，因为全局只需要一个Retrofit
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    //创建Retrofit实例
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    //创建Service接口的代理对象
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    //使用内联函数，泛型实例化
    inline fun <reified T> create(): T = create(T::class.java)
}










































