package at.tugraz.iti.mcl.project.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.window.Dialog
import at.tugraz.iti.mcl.project.User

@Composable
fun LearnUserDialog(
    learnUserDialogUser: MutableState<User?>,
    sensorData: SnapshotStateList<Array<Float>>
) {
    if (learnUserDialogUser.value == null) {
        return
    }

    Dialog(onDismissRequest = { learnUserDialogUser.value = null }) {
        DialogContent(
            icon = Icons.Default.Build,
            title = "Learn User",
            text = "Processing sensor data to learn user...",
            content = { SensorData(sensorData = sensorData)},
            doneButtonOnClick = { learnUserDialogUser.value = null })
    }
}