package dev.dcxo.weatherapp.ui.components

import android.icu.text.DecimalFormat
import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dcxo.weatherapp.ui.theme.Typography

const val METRIC_UNIT_CELSIUS = "℃"
const val METRIC_UNIT_FAHRENHEIT = "℉"

/**
 * @param content Temperature on kelvin
 */
@Composable
fun TextTempUnit(
    content: String?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    localeOverride: ULocale? = null,
    unitModifier: Modifier = Modifier,
    unitTextStyle: TextStyle = textStyle
) {
    if (content.isNullOrBlank()) return

    val parsedContent = content.toDouble()

    val locale = localeOverride ?: ULocale.getDefault()
    val (unit, transformedContent) = when (LocaleData.getMeasurementSystem(locale)) {
        LocaleData.MeasurementSystem.US, LocaleData.MeasurementSystem.UK -> METRIC_UNIT_FAHRENHEIT to kelvinToFahrenheit(
            parsedContent
        )

        LocaleData.MeasurementSystem.SI -> METRIC_UNIT_CELSIUS to kelvinToCelsius(parsedContent)
        else -> "K" to parsedContent
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.Top
    ) {
        Text(DecimalFormat.getInstance().apply { maximumFractionDigits = 2 }
            .format(transformedContent), modifier = modifier, style = textStyle)
        Text(unit, modifier = unitModifier, style = unitTextStyle)
    }
}

private fun kelvinToFahrenheit(kelvin: Double): Double = ((kelvin - 273.15) * 1.8) + 32
private fun kelvinToCelsius(kelvin: Double): Double = kelvin - 273.15

@Preview(locale = "en", showBackground = true)
@Composable
fun TempUnitFahrenheitPreview() {
    TextTempUnit(content = "300")
}

@Preview(locale = "es", showBackground = true)
@Composable
fun TempUnitCelsiusOverridePreview() {
    TextTempUnit(content = "300")
}

@Preview(locale = "es", showBackground = true)
@Composable
fun TempUnitTextStylesOverridePreview() {
    TextTempUnit(
        content = "300",
        textStyle = Typography.headlineLarge.copy(fontWeight = FontWeight.Black),
        unitTextStyle = Typography.titleMedium.copy(color = Color.Black.copy(alpha = 0.4f))
    )
}

@Preview
@Composable
fun TempUnitWithoutText() {
    TextTempUnit(content = null)
}