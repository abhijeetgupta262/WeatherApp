package com.app.weatherapp.ui.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.weatherapp.R
import com.app.weatherapp.mvp.model.Forecastday
import com.app.weatherapp.ui.home.adapter.viewholder.ForecastItemViewHolder

class ForecastListAdapter(private val context: Context, private val dataSource: List<Forecastday>) : RecyclerView.Adapter<ForecastItemViewHolder>() {


    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_list, parent, false)
        return ForecastItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ForecastItemViewHolder, position: Int) {
        if (dataSource.size > position) {
            holder.bindItems(context, dataSource[position])
        }
    }

}