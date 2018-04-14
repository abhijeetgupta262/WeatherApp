package com.app.weatherapp.mvp.presenter

import com.app.weatherapp.BuildConfig
import com.app.weatherapp.mvp.BasePresenter
import com.app.weatherapp.mvp.model.ForecastData
import com.app.weatherapp.mvp.view.HomeView
import com.app.weatherapp.network.WeatherAPIFactory
import com.app.weatherapp.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * This class is used for HomePresenter of HomeActivity.
 * This presenter is used to perform the all business logic and inform the view to update.
 * @param homeView : HomeView
 */
class HomePresenter(val homeView: HomeView) : BasePresenter() {

    /* =================================== Class Variable ======================================= */

    // Variable for CompositeDisposable
    private var mSubscription: CompositeDisposable = CompositeDisposable()

    /* ============================= Implemented Interface Method =============================== */

    override fun destroy() {
        // Dispose subscription
        RxUtils.disposeIfNotNull(mSubscription)
    }

    /* ================================== User Define Method ==================================== */

    /**
     * This method is used to perform the network request for weather forecast.
     * @param location : Location for weather forecast
     * @param days : Number of days
     */
    fun getWeatherForecast(location: String, days: Int) {
        // Passing callback for request start
        homeView.onRequestStart()

        // Subscribe for forecast request
        mSubscription.add(WeatherAPIFactory.create()
                .getWeatherForecast(BuildConfig.APIUX_KEY, location, days)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ t: ForecastData ->
                    // Passing callback to host for success
                    homeView.onForecastFetched(t)
                }, { error ->
                    // Passing callback to host for error
                    homeView.onRequestError(error.localizedMessage)
                })
        )
    }
}