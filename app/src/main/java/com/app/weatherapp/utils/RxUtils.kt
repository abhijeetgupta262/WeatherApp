package com.app.weatherapp.utils

import io.reactivex.disposables.CompositeDisposable

/**
 * This class is used to hold the utility methods for RXAndroid.
 */
class RxUtils
{
     companion object {
         /**
          * This method is used to dispose the CompositeDisposable.
          * @param subscription - Reference for the CompositeDisposable.
          */
         fun disposeIfNotNull(subscription: CompositeDisposable?) {
             subscription?.dispose()
         }
     }
}