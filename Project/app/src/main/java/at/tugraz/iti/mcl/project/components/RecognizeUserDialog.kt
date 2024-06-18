package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecognizeUserDialog(recognizeUserDialogVisible: MutableState<Boolean>) {
    if (!recognizeUserDialogVisible.value) {
        return
    }

    Dialog(onDismissRequest = { recognizeUserDialogVisible.value = false }) {
        DialogContent(
            icon = Icons.Default.Person,
            title = "Recognize User",
            text = "Processing sensor data to recognize user...",
            doneButtonOnClick = { recognizeUserDialogVisible.value = false },
            content = {
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
                        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Face, contentDescription = null)
                        Text(text = "Unknown User", modifier = Modifier
                            .padding(start = 5.dp)
                            .padding(end = 10.dp))
                        CircularProgressIndicator(modifier = Modifier
                            .width(20.dp)
                            .padding(top = 20.dp))
                    }
                }
            }
        )
    }
}