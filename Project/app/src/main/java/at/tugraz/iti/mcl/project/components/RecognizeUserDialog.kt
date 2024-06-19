package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import at.tugraz.iti.mcl.project.User
import at.tugraz.iti.mcl.project.data

@Composable
fun RecognizeUserDialog(
    recognizeUserDialogVisible: MutableState<Boolean>,
    sensorData: SnapshotStateList<Array<Float>>,
    recognizedUser: MutableState<User?>
) {
    if (!recognizeUserDialogVisible.value) {
        return
    }

    data.clear()

    fun done() {
        recognizeUserDialogVisible.value = false
        recognizedUser.value = null
    }

    Dialog(onDismissRequest = { done() }) {
        DialogContent(
            icon = Icons.Default.Person,
            title = "Recognize User",
            text = "Processing sensor data to recognize user...",
            doneButtonOnClick = { done() },
            content = {
                SensorData(sensorData = sensorData, modifier = Modifier.padding(bottom = 25.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 25.dp, vertical = 15.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Face, contentDescription = null)
                        Text(text = if (recognizedUser.value == null) "Unknown User" else recognizedUser.value!!.firstName, modifier = Modifier
                            .padding(start = 5.dp)
                            .padding(end = 10.dp))
                    }
                }
            }
        )
    }
}