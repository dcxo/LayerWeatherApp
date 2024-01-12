package dev.dcxo.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dev.dcxo.weatherapp.models.CityWeather
import dev.dcxo.weatherapp.services.OpenWeatherMapService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel(private val api: OpenWeatherMapService) : ViewModel() {

    private val _weather: MutableStateFlow<CityWeather?> = MutableStateFlow(null)
    val weather = _weather.asStateFlow()

    fun getWeatherByCity(cityName: String) {
        viewModelScope.launch {
            val data = api.getWeatherByCity(cityName)
            _weather.emit(data)
        }
    }


    class Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>, extras: CreationExtras
        ): T {

            val httpClient = OkHttpClient.Builder().addInterceptor {
                it.proceed(
                    it.request().newBuilder().url(
                        it.request().url().newBuilder()
                            .addQueryParameter(
                                "appid", BuildConfig.OpenWeatherMapApiKey
                            ).build()
                    ).build()
                )
            }.build()

            val retrofit = Retrofit.Builder().baseUrl(
                HttpUrl.Builder().scheme("https").host("api.openweathermap.org").build()
            ).addConverterFactory(GsonConverterFactory.create()).client(httpClient).build()

            val api = retrofit.create(OpenWeatherMapService::class.java)

            return WeatherViewModel(api = api) as T
        }
    }

}

