package com.app.weatherapp.utils

import io.reactivex.disposables.CompositeDisposable

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