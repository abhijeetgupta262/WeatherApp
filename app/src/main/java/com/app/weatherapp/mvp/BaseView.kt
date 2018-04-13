package com.app.weatherapp.mvp

interface BaseView
{
    fun onRequestStart()

    fun onRequestError(errorMessage: String)

}