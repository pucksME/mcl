package at.tugraz.iti.mcl.project

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.Date

class AccelerationSensorEventListener(
    private val sensorData: SnapshotStateList<Array<Float>>
) : SensorEventListener {
    private var updatedTime = Date().time

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.values == null) {
            return
        }

        val time = Date().time

        if (time - updatedTime < 50) {
            return
        }

        sensorData.add(event.values.toTypedArray())

        if (sensorData.size > 500) {
            sensorData.removeAt(0)
        }

        updatedTime = time
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}