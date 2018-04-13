package com.app.weatherapp.network

import com.app.weatherapp.mvp.model.ForecastData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET(APIConstants.HISTORY_ACTION)
    fun getWeatherForecast(@Query("key") key: String, @Query("q") q: String, @Query("days") days: Int): Observable<ForecastData>
}