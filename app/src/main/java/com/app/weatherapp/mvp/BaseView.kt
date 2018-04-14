package com.app.weatherapp.mvp

/**
 * This class/interface is used for BaseView. The BaseView is super interface for
 * all other views of MVC in the app and we can add some common methods in this interface that is common
 * to all Views.
 */
interface BaseView
{
    /**
     * This method is used to inform the host that request is begin to execute.
     */
    fun onRequestStart()

    /**
     * This method is used to inform the host that request return Error.
     * @param errorMessage - Error message.
     */
    fun onRequestError(errorMessage: String)

}