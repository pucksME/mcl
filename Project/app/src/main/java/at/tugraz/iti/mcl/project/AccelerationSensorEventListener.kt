package at.tugraz.iti.mcl.project

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.Date

class AccelerationSensorEventListener(
    private val data: ArrayList<Array<Float>>,
    private val sensorData: SnapshotStateList<Array<Float>>,
    private val recognizedUser: MutableState<User?>,
    private val recognizeUserDialogVisible: MutableState<Boolean>
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
        data.add(event.values.toTypedArray())

        if (recognizeUserDialogVisible.value && data.size / 40 >= 1) {
            handleRecognizeUserDone(recognizedUser)
        }

        //System.out.println(data.size / 40)

        if (sensorData.size > 500) {
            sensorData.removeAt(0)
        }

        updatedTime = time
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}