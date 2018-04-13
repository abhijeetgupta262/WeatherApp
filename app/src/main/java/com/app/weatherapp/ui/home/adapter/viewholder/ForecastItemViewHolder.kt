package com.app.weatherapp.ui.home.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.weatherapp.mvp.model.Forecastday
import com.app.weatherapp.utils.Utilities
import kotlinx.android.synthetic.main.item_forecast_list.view.*

class ForecastItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    fun bindItems(context: Context, forecastDay: Forecastday) = with(itemView)
    {
        itemView.txt_item_forecast_date.text = Utilities.formatToYesterdayOrToday(forecastDay.date)
        itemView.txt_item_forecast_status.text = forecastDay.day.condition.text
        var name = "day_" + forecastDay.day.condition.icon.replace("//cdn.apixu.com/weather/64x64/day/", "")
        name = name.replace(".png", "")
        itemView.img_item_forecast_status_icon.setImageResource(itemView.resources.getIdentifier(name, "drawable", context.packageName))
        itemView.txt_item_forecast_temp.text = String.format("%d°/%d°", forecastDay.day.mintemp_c.toInt(), forecastDay.day.maxtemp_c.toInt())
    }
}