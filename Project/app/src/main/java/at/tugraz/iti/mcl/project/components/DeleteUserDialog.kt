package at.tugraz.iti.mcl.project.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import at.tugraz.iti.mcl.project.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteUserDialog(deleteUserDialogUser: MutableState<String>, users: SnapshotStateList<User>, userDetailsUser: MutableState<User?>) {
    if (deleteUserDialogUser.value.isEmpty()) {
        return
    }

    AlertDialog(
        icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = null ) },
        title = { Text(text = "Delete User") },
        text = { Text(text = "Do you really want to delete this user?") },
        confirmButton = { TextButton(onClick = {
            users.removeAll { currentUser -> currentUser.firstName.equals(deleteUserDialogUser.value) }
            deleteUserDialogUser.value = ""
            userDetailsUser.value = null
        }) {
            Text(text = "Delete")
        }
        },
        dismissButton = { TextButton(onClick = { deleteUserDialogUser.value = "" }) {
            Text(text = "Cancel")
        }
        },
        onDismissRequest = { deleteUserDialogUser.value = "" }
    );
}