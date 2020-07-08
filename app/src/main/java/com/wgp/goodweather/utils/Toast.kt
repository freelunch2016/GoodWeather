package com.wgp.goodweather.utils

import android.widget.Toast
import com.wgp.goodweather.GoodWeatherApplication
import java.time.Duration


fun String.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(GoodWeatherApplication.context,this,duration).show()
}

//int resId
fun Int.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(GoodWeatherApplication.context,this,duration).show()
}
