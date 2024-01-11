package dev.dcxo.weatherapp.services

import dev.dcxo.weatherapp.models.CityWeather
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherMapService {
    @GET("data/2.5/weather")
    suspend fun getWeatherByCity(@Query("q") cityName: String): CityWeather
}