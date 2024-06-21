package at.tugraz.iti.mcl.project.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.window.Dialog
import at.tugraz.iti.mcl.project.MainActivity
import at.tugraz.iti.mcl.project.User
import at.tugraz.iti.mcl.project.data
import at.tugraz.iti.mcl.project.handleLearUserDone

@Composable
fun LearnUserDialog(
    learnUserDialogUser: MutableState<User?>,
    sensorData: SnapshotStateList<Array<Float>>,
) {
    if (learnUserDialogUser.value == null) {
        return
    }

    fun done() {
        learnUserDialogUser.value = null
        handleLearUserDone()
    }

    data.clear()

    Dialog(onDismissRequest = { done() }) {
        DialogContent(
            icon = Icons.Default.Build,
            title = "Learn User",
            text = "Processing sensor data to learn user...",
            content = { SensorData(sensorData = sensorData)},
            doneButtonOnClick = { done() })
    }
}