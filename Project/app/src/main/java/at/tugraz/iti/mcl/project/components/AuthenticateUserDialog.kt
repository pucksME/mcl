package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import at.tugraz.iti.mcl.project.User

@Composable
fun AuthenticateUserDialog(
    authenticateUserDialogUser: MutableState<User?>,
    sensorData: SnapshotStateList<Array<Float>>
) {
    if (authenticateUserDialogUser.value == null) {
        return
    }

    Dialog(onDismissRequest = { authenticateUserDialogUser.value = null }) {
        DialogContent(
            icon = Icons.Default.Lock,
            title = "Authenticate User",
            text = "Processing sensor data to authenticate user...",
            content = { SensorData(sensorData = sensorData, modifier = Modifier.padding(bottom = 25.dp)) },
            doneButtonOnClick = { authenticateUserDialogUser.value = null }
        )
    }
}