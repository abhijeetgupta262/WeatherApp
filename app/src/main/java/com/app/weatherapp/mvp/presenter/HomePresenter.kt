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

class HomePresenter(val homeView: HomeView) : BasePresenter() {

    private var mSubscription: CompositeDisposable = CompositeDisposable()

    override fun destroy()
    {
        // Dispose subscription
        RxUtils.disposeIfNotNull(mSubscription)
    }

    fun getForecastHistory()
    {
        // Passing callback for request start
        homeView.onRequestStart()

        // Subscribe for forecast request
        mSubscription.add(WeatherAPIFactory.create()
                .getWeatherForecast(BuildConfig.APIUX_KEY, "Bengaluru", 7)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ t: ForecastData ->
                    homeView.onForecastFetched(t)
                }, { error ->
                    error.printStackTrace()
                    homeView.onRequestError(error.localizedMessage)
                })
        )
    }
}