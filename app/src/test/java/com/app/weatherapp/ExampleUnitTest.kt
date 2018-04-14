package com.app.weatherapp

import com.app.weatherapp.utils.Utilities
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun formatToYesterdayOrToday_isCorrect()
    {
        var forecastDate = "2018-04-15"
        val output = "15 Apr 2018"
        var res = Utilities.formatDateToYesterdayOrToday(forecastDate)
        assertEquals("",output, res)
    }

}
