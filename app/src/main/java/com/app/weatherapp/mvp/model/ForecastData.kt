package com.app.weatherapp.mvp.model

/**
 * This class/model is used for holding the temperature forecast data.
 */
data class ForecastData(
        val location: Location,
        val current: Current,
        val forecast: Forecast
)

data class Location(
        val name: String,
        val region: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val tz_id: String,
        val localtime_epoch: Int,
        val localtime: String
)

data class Current(
        val last_updated_epoch: Int,
        val last_updated: String,
        val temp_c: Int,
        val temp_f: Double,
        val is_day: Int,
        val condition: Condition,
        val wind_mph: Double,
        val wind_kph: Double,
        val wind_degree: Int,
        val wind_dir: String,
        val pressure_mb: Int,
        val pressure_in: Double,
        val precip_mm: Double,
        val precip_in: Double,
        val humidity: Int,
        val cloud: Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val vis_km: Int,
        val vis_miles: Int
)

data class Condition(
        val text: String,
        val icon: String,
        val code: Int
)

data class Forecast(
        val forecastday: List<Forecastday>
)

data class Forecastday(
        val date: String,
        val date_epoch: Int,
        val day: Day,
        val astro: Astro
)

data class Astro(
        val sunrise: String,
        val sunset: String,
        val moonrise: String,
        val moonset: String
)

data class Day(
        val maxtemp_c: Double,
        val maxtemp_f: Double,
        val mintemp_c: Double,
        val mintemp_f: Double,
        val avgtemp_c: Double,
        val avgtemp_f: Double,
        val maxwind_mph: Double,
        val maxwind_kph: Double,
        val totalprecip_mm: Double,
        val totalprecip_in: Double,
        val avgvis_km: Double,
        val avgvis_miles: Int,
        val avghumidity: Int,
        val condition: Condition,
        val uv: Double
)