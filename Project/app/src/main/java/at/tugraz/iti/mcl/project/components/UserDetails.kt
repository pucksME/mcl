package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.tugraz.iti.mcl.project.User

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserDetails(userDetailsUser: MutableState<User?>, deleteUserDialogUser: MutableState<String>) {
    if (userDetailsUser.value == null) {
        return
    }

    ModalBottomSheet(onDismissRequest = { userDetailsUser.value = null }) {
        Column(modifier = Modifier
            .padding(bottom = 50.dp)
            .padding(horizontal = 25.dp)) {
            Column {
                UserInformation(user = userDetailsUser.value)
            }
            Column(modifier = Modifier.padding(top = 10.dp)) {
                FlowRow {
                    IconTextButton(
                        imageVector = Icons.Default.Lock,
                        text = "Authenticate",
                        onClick = {},
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    IconTextButton(
                        imageVector = Icons.Default.Build,
                        text = "Learn",
                        onClick = {},
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    IconTextButton(
                        imageVector = Icons.Default.Delete,
                        text = "Delete",
                        onClick = { deleteUserDialogUser.value = userDetailsUser.value!!.firstName },
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
            }
        }
    }
}