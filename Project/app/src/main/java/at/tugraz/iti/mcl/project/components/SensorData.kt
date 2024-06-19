package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SensorData(sensorData: SnapshotStateList<Array<Float>>, modifier: Modifier = Modifier) {
    val colors = arrayOf(Color.Gray, Color.LightGray)
    Column(modifier = modifier) {
        Canvas(modifier = Modifier
            .height(75.dp)
            .fillMaxWidth()
            .clipToBounds()) {
            val sensorDataWindow = sensorData.subList(Math.max(sensorData.size - 29, 0), Math.max(sensorData.size - 1, 0))
            for (index in sensorDataWindow.indices) {
                drawCircle(
                    color = colors.random(),
                    radius = 10f,
                    center=Offset(x=index * 25f, y=size.height - 10 - Math.abs(sensorDataWindow[index][1] * 30))
                )
            }
        }
    }
}