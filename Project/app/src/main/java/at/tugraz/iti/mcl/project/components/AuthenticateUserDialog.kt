package at.tugraz.iti.mcl.project.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.Dialog
import at.tugraz.iti.mcl.project.User

@Composable
fun AuthenticateUserDialog(authenticateUserDialogUser: MutableState<User?>) {
    if (authenticateUserDialogUser.value == null) {
        return
    }

    Dialog(onDismissRequest = { authenticateUserDialogUser.value = null }) {
        DialogContent(
            icon = Icons.Default.Lock,
            title = "Authenticate User",
            text = "Processing sensor data to authenticate user...",
            content = {},
            doneButtonOnClick = { authenticateUserDialogUser.value = null }
        )
    }
}