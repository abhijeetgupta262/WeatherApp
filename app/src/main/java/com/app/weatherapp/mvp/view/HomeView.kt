package com.app.weatherapp.mvp.view

import com.app.weatherapp.mvp.BaseView
import com.app.weatherapp.mvp.model.ForecastData

/**
 * This interface is used for HomeActivity and passing callback to host with appropriate response.
 */
interface HomeView : BaseView
{
    /**
     * This callback method is used for forecast request successful response.
     * @param response - ForecastData
     */
    fun onForecastFetched(response: ForecastData)
}