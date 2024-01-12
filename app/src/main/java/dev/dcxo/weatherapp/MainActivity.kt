package dev.dcxo.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import dev.dcxo.weatherapp.ui.components.Layer
import dev.dcxo.weatherapp.ui.components.TextTempUnit
import dev.dcxo.weatherapp.ui.components.VideoPlayer
import dev.dcxo.weatherapp.ui.theme.Typography
import dev.dcxo.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoUri = getString(R.string.video_url).toUri()

        setContent {

            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    var pointsLeft by remember { mutableStateOf(true) }
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(30.seconds)
                            pointsLeft = !pointsLeft
                        }
                    }

                    VideoPlayer(url = videoUri)
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Layer(
                            color = Color.Yellow,
                            size = 150.dp,
                            pointsLeft = pointsLeft,
                            modifier = Modifier.align(if (pointsLeft) Alignment.Start else Alignment.End)
                        ) {
                            val weatherViewModel by viewModels<WeatherViewModel> { WeatherViewModel.Factory() }
                            val weather by weatherViewModel.weather.collectAsState()


                            LaunchedEffect(true) {
                                weatherViewModel.getWeatherByCity("London")
                            }

                            weather?.let {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom,
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Text(it.name ?: "", color = Color.Black, style = Typography.titleMedium)
                                    TextTempUnit(it.main?.temp?.toString(), textStyle = Typography.titleLarge.copy(Color.Black))
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}