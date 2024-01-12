package dev.dcxo.weatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.text.util.LocalePreferences.TemperatureUnit

val PointLeftTriangleShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(0f, size.height)
    lineTo(0f, 0f)
}
val PointRightTriangleShape = GenericShape { size, _ ->
    moveTo(size.width, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, 0f)
    lineTo(size.width, 0f)
}

@Composable
fun Layer(
    color: Color,
    size: Dp,
    modifier: Modifier = Modifier,
    pointsLeft: Boolean = true,
    content: @Composable () -> Unit
) {

    Box {
        Surface(
            shape = if (pointsLeft) PointLeftTriangleShape else PointRightTriangleShape,
            color = color,
            modifier = Modifier.size(size)
        ) {}
        Surface(
            color = color,
            modifier = Modifier
                .rotate(45f * if (pointsLeft) -1 else 1)
                .size(size)
                .offset(0.dp, (-size / 2))
                .then(modifier),
            content = content
        )
    }

}

@Preview
@Composable
fun LayerPointsLeftPreview() {
    Layer(
        color = Color.Yellow, size = 150.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text("Córdoba")
            TextTempUnit(content = "300")
        }
    }
}

@Preview
@Composable
fun LayerPointsRightPreview() {
    Layer(
        color = Color.Yellow, size = 150.dp, pointsLeft = false
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text("Córdoba")
            TextTempUnit(content = "300")
            TemperatureUnit.CELSIUS
        }
    }
}