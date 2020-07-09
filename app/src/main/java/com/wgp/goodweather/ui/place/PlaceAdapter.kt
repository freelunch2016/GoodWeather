package com.wgp.goodweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.wgp.goodweather.MainActivity
import com.wgp.goodweather.R
import com.wgp.goodweather.logic.model.Place
import com.wgp.goodweather.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*


class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName = itemView.findViewById<TextView>(R.id.placeName)
        val placeAddress = itemView.findViewById<TextView>(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        val viewHolder = ViewHolder(itemView)

        viewHolder.itemView.setOnClickListener {
            val placeFragment = fragment as PlaceFragment
            val position = viewHolder.bindingAdapterPosition
            val place = placeList[position]
            val activity = placeFragment.activity
            //如果是在天气页面，就不进行页面跳转
            if (activity is WeatherActivity) {
                activity.drawerLayout.closeDrawers()
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            } else {
                WeatherActivity.startWeatherActivity(
                    parent.context,
                    place.location.lat,
                    place.location.lng,
                    place.name
                )
                placeFragment.activity?.finish()
            }
            //存储被点击的地址
            placeFragment.viewModel.savePlace(place)

        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}