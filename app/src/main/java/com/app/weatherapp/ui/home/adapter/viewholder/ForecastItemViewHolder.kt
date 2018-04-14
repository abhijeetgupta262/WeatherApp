package com.app.weatherapp.ui.home.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.weatherapp.mvp.model.Forecastday
import com.app.weatherapp.utils.Utilities
import kotlinx.android.synthetic.main.item_forecast_list.view.*

/**
 * This class is used as the ViewHolder for the forecast list row.
 * @param item : View of row
 */
class ForecastItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    /* ================================== User Define Method ==================================== */

    /**
     * This method is used to bind the forecast list row to forecast data.
     * @param context : Current context
     * @param forecastDay : Forecastday
     */
    fun bindItems(context: Context, forecastDay: Forecastday) = with(itemView)
    {
        // Set the data for row according to Forecastday
        itemView.txt_item_forecast_date.text = Utilities.formatDateToYesterdayOrToday(forecastDay.date)
        itemView.txt_item_forecast_status.text = forecastDay.day.condition.text
        var name = "day_" + forecastDay.day.condition.icon.replace("//cdn.apixu.com/weather/64x64/day/", "")
        name = name.replace(".png", "")
        itemView.img_item_forecast_status_icon.setImageResource(itemView.resources.getIdentifier(name, "drawable", context.packageName))
        itemView.txt_item_forecast_temp.text = String.format("%d°/%d°", forecastDay.day.mintemp_c.toInt(), forecastDay.day.maxtemp_c.toInt())
    }
}