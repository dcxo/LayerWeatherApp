package dev.dcxo.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.dcxo.weatherapp.ui.components.TextTempUnit
import dev.dcxo.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val weatherViewModel by viewModels<WeatherViewModel> { WeatherViewModel.Factory() }
                    val weather by weatherViewModel.weather.collectAsState()

                    LaunchedEffect (true) {
                        weatherViewModel.getWeatherByCity("Cordoba")
                    }

                    weather?.let {
                        Column {
                            Text(it.name ?: "")
                            TextTempUnit(it.main?.temp?.toString())
                        }
                    }

                }
            }
        }
    }
}