package com.amar.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.amar.weatherapp.R
import com.amar.weatherapp.model.WeatherInfo
import com.amar.weatherapp.util.Constants.BASE_URL
import com.bumptech.glide.Glide

class WeatherInfoRecyclerViewAdapter(private var dataSet: List<WeatherInfo>) : RecyclerView.Adapter<WeatherInfoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_info_listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weatherInfo = dataSet.get(position)

        viewHolder.hourTV.text = weatherInfo.dt_txt.subSequence(11, 16)
        viewHolder.tempTV.text = String.format("%.1f °C", weatherInfo.main.temp - 273.15)

        val iconUrl = String.format("%simg/wn/%s@2x.png", BASE_URL, weatherInfo.weather.get(0).icon)
        Glide.with(viewHolder.iconImage).load(iconUrl).into(viewHolder.iconImage);
    }

    override fun getItemCount() = dataSet.size

    fun setItems(list: List<WeatherInfo>) {
        dataSet = list
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hourTV: TextView
        val tempTV: TextView
        val iconImage: AppCompatImageView

        init {
            hourTV = view.findViewById(R.id.hourTV)
            tempTV = view.findViewById(R.id.tempTV)
            iconImage = view.findViewById(R.id.icon)
        }
    }

}