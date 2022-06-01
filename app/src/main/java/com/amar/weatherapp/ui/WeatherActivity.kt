package com.amar.weatherapp.ui

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.amar.weatherapp.databinding.ActivityWeatherBinding
import com.amar.weatherapp.ui.adapters.DaysRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    val viewModel: WeatherViewModel by viewModels()
    lateinit var adapter: DaysRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.daysRecyclerView)

        binding.daysRecyclerView.setLayoutManager(object : LinearLayoutManager(this, HORIZONTAL, false) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width * 0.7).toInt()
                return true
            }
        })

        binding.switchLive.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                viewModel.setIsLive(isChecked)
            }
        })

        setSubscribers()

        getData()
    }

    private fun getData() {
        viewModel.getWeatherData()
    }

    private fun setSubscribers() {
        lifecycleScope.launchWhenCreated {
            viewModel.weatherHashMap.collect {
                adapter = DaysRecyclerViewAdapter(it)
                binding.daysRecyclerView.adapter = adapter;
            }
        }
    }

}