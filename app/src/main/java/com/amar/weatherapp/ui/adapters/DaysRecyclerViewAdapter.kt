package com.amar.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amar.weatherapp.R
import com.amar.weatherapp.model.WeatherInfo

class DaysRecyclerViewAdapter(private var dataSet: Map<String, List<WeatherInfo>>) : RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.day_info_listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val key = dataSet.keys.toList().get(position)
        val weatherInfoList = dataSet[key]

        viewHolder.dateTV.text = key

        val adapter = WeatherInfoRecyclerViewAdapter(weatherInfoList ?: listOf());
        viewHolder.weatherInfoRecyclerView.adapter = adapter
    }

    override fun getItemCount() = dataSet.size

    fun setItems(list: HashMap<String, List<WeatherInfo>>) {
        dataSet = list
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTV: TextView
        val weatherInfoRecyclerView: RecyclerView

        init {
            dateTV = view.findViewById(R.id.dateTv)
            weatherInfoRecyclerView = view.findViewById(R.id.weatherInfoRecyclerView)
        }
    }

}