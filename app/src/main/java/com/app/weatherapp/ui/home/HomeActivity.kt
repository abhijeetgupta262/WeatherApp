package com.app.weatherapp.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.app.weatherapp.R
import com.app.weatherapp.mvp.model.ForecastData
import com.app.weatherapp.mvp.presenter.HomePresenter
import com.app.weatherapp.mvp.view.HomeView
import com.app.weatherapp.ui.BaseActivity
import com.app.weatherapp.ui.home.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), HomeView {

    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    private lateinit var homePresenter: HomePresenter

    /* ================================== Life Cycle Method ===================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenter(this)

        // Initialize Views
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Inform the presenter for state
        homePresenter.destroy()
    }

    /* ============================= Implemented Interface Method =============================== */

    override fun onRequestStart() {
        // Update view for loading
        updateViewForLoading()
    }

    override fun onForecastFetched(response: ForecastData) {
        Log.e("Response", response.toString())
        // Update view for forecast
        updateViewForForecast(response)
    }

    override fun onRequestError(errorMessage: String) {
        // Update view for error
        updateViewForError()
    }


    /* ================================== User Define Method ==================================== */

    private fun initViews() {
        // Subscribe for forecast
        subscribeForForecasts()

        txt_home_retry.setOnClickListener {
            // Subscribe for Forecast
            subscribeForForecasts()
        }
    }

    private fun updateViewForLoading() {
        txt_home_loading.visibility = View.VISIBLE
        linear_home_temp_container.visibility = View.GONE
        linear_home_error_container.visibility = View.GONE
    }

    private fun updateViewForForecast(forecast: ForecastData) {
        txt_home_loading.visibility = View.GONE
        linear_home_temp_container.visibility = View.VISIBLE
        linear_home_error_container.visibility = View.GONE

        txt_home_temp.text = String.format("%d°", forecast.current.temp_c)
        txt_home_city.text = forecast.location.name

        var adapter = ForecastListAdapter(this, forecast.forecast.forecastday)
        var linearLayoutManager = LinearLayoutManager(this)

        rcv_home_forecast_list.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(rcv_home_forecast_list.context, linearLayoutManager.orientation)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        rcv_home_forecast_list.addItemDecoration(dividerItemDecoration)
        rcv_home_forecast_list.adapter = adapter

        //Slide Up List Panel
        startSlideUpListPanelAnimation()
    }

    private fun updateViewForError() {
        txt_home_loading.visibility = View.GONE
        linear_home_temp_container.visibility = View.GONE
        linear_home_error_container.visibility = View.VISIBLE
    }

    private fun subscribeForForecasts() {
        homePresenter.getForecastHistory()
    }


    private fun startSlideUpListPanelAnimation() {
        //Slide Up List Panel
        val slide_up = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        rel_home_list_container.startAnimation(slide_up)
    }
}
