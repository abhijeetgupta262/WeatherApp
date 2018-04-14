package com.app.weatherapp.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import com.app.weatherapp.R
import com.app.weatherapp.common.Constants
import com.app.weatherapp.mvp.model.ForecastData
import com.app.weatherapp.mvp.presenter.HomePresenter
import com.app.weatherapp.mvp.view.HomeView
import com.app.weatherapp.ui.BaseActivity
import com.app.weatherapp.ui.home.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_home.*

/**
 * This class/activity is used for HomeActivity of app. The HomeActivity contains three views -
 * 1. View for Loading state
 * 2. View for showing weather forecast
 * 3. View for showing error
 */
class HomeActivity : BaseActivity(), HomeView {

    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Presenter
    private lateinit var homePresenter: HomePresenter

    /* ================================== Life Cycle Method ===================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize the presenter
        homePresenter = HomePresenter(this)

        // Initialize Views
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Inform presenter for destroy
        homePresenter.destroy()
    }

    /* ============================= Implemented Interface Method =============================== */

    override fun onRequestStart() {
        // Update UI for loading
        updateViewForLoading()
    }

    override fun onForecastFetched(response: ForecastData) {
        // Update UI according to response
        updateViewForForecast(response)
    }

    override fun onRequestError(errorMessage: String) {
        // Update UI according to error
        updateViewForError()
    }


    /* ================================== User Define Method ==================================== */

    /**
     * This method is used to initialize the view for home activity.
     */
    private fun initViews() {
        // Subscribe for forecast
        subscribeForForecasts()

        // Attach the OnClick Listener for Retry button
        txt_home_retry.setOnClickListener {
            // Subscribe for Forecast
            subscribeForForecasts()
        }
    }

    /**
     * This method is used to update the ui for loading.
     */
    private fun updateViewForLoading() {
        // Update the views visibility
        txt_home_loading.visibility = View.VISIBLE
        linear_home_temp_container.visibility = View.GONE
        linear_home_error_container.visibility = View.GONE
    }

    /**
     * This method is used to update the ui for successful response of forecast API.
     */
    private fun updateViewForForecast(forecast: ForecastData) {
        // Update the views visibility
        txt_home_loading.visibility = View.GONE
        linear_home_temp_container.visibility = View.VISIBLE
        linear_home_error_container.visibility = View.GONE
        // Set the temperature and location
        txt_home_temp.text = String.format("%d°", forecast.current.temp_c)
        txt_home_city.text = forecast.location.name

        // Initialize and set the adapter for recycler view
        var adapter = ForecastListAdapter(this, forecast.forecast.forecastday)
        var linearLayoutManager = LinearLayoutManager(this)
        rcv_home_forecast_list.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(rcv_home_forecast_list.context, linearLayoutManager.orientation)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        rcv_home_forecast_list.addItemDecoration(dividerItemDecoration)
        rcv_home_forecast_list.adapter = adapter

        // Slide Up List Panel
        startSlideUpListPanelAnimation()
    }

    /**
     * This method is used to update the ui for unsuccessful response of forecast API.
     */
    private fun updateViewForError() {
        // Update the views visibility
        txt_home_loading.visibility = View.GONE
        linear_home_temp_container.visibility = View.GONE
        linear_home_error_container.visibility = View.VISIBLE
    }

    /**
     * This method is used to subscribe for the weather forecast API.
     */
    private fun subscribeForForecasts() {
        // Inform presenter to subscribe for forecast API
        homePresenter.getWeatherForecast(Constants.DEFAULT_LOCATION, Constants.DEFAULT_DAYS)
    }

    /**
     * This method is used to start the slide up animation for bottom panel.
     */
    private fun startSlideUpListPanelAnimation() {
        //Slide Up List Panel
        val slideUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        rel_home_list_container.startAnimation(slideUp)
    }
}
