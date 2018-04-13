package com.app.weatherapp.mvp.view

import com.app.weatherapp.mvp.BaseView
import com.app.weatherapp.mvp.model.ForecastData

interface HomeView : BaseView {
    fun onForecastFetched(response: ForecastData)
}