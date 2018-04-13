package com.app.weatherapp.network

import com.app.weatherapp.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit



class WeatherAPIFactory private constructor()
{
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */


    /* =================================== Getter - Setter Method =============================== */

    companion object Factory
    {
        fun create() : WeatherAPIService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .build()
            return retrofit.create(WeatherAPIService::class.java)
        }
    }

    /* ================================== Life Cycle Method ===================================== */


    /* ============================= Implemented Interface Method =============================== */


    /* ==================================== OnClick Methods ===================================== */


    /* ================================== User Define Method ==================================== */


    /* ==================================== Inner Classes ======================================  */
}