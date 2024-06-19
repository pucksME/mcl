package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.tugraz.iti.mcl.project.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserForm(users: SnapshotStateList<User>, createUserFormVisible: MutableState<Boolean>) {
    if (!createUserFormVisible.value) {
        return
    }

    var firstname by remember {
        mutableStateOf("")
    }

    ModalBottomSheet(onDismissRequest = { createUserFormVisible.value = false }) {
        val invalidInput = remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier
            .padding(bottom = 50.dp)
            .padding(horizontal = 25.dp)) {
            Heading(text = "Register User")
            Column(modifier = Modifier
                .fillMaxWidth()){
                TextField(
                    value = firstname,
                    onValueChange = {value -> firstname = value},
                    label = { Text("First name") },
                    isError = invalidInput.value,
                    supportingText = { if (invalidInput.value) Text(text = "Please choose a name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    if (firstname.isEmpty()) {
                        invalidInput.value = true
                    } else {
                        invalidInput.value = false
                        users.add(User(firstname, false))
                        createUserFormVisible.value = false
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)) {
                    Row(modifier = Modifier.padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = "Add new user".uppercase(), modifier = Modifier.padding(start = 2.dp))
                    }
                }
            }
        }
    }
}