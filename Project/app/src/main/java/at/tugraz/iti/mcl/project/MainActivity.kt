package at.tugraz.iti.mcl.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.tugraz.iti.mcl.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val users = remember {
                        mutableStateListOf(User("User 1", true), User("User 2", false))
                    }

                    val deleteUserDialogUser = remember {
                        mutableStateOf("")
                    }

                    val userDetailsUser = remember {
                        mutableStateOf<User?>(null)
                    }

                    val createUserFormVisible = remember {
                        mutableStateOf(false)
                    }

                    Scaffold (floatingActionButton = { AddUser(createUserFormVisible) }){ innerPadding ->
                        Column (modifier = Modifier.padding(innerPadding)){
                            DeleteUserDialog(deleteUserDialogUser, users, userDetailsUser)
                            UserDetails(userDetailsUser, deleteUserDialogUser)
                            CreateUserForm(users, createUserFormVisible)
                            // AddUser()
                            Title()
                            UserList(users, userDetailsUser)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Title() {
    Text(
        text = "Known Users",
        fontSize = 25.sp,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        fontSize = 50.sp,
        modifier = modifier
    )
    Icon(imageVector = Icons.Default.Add, contentDescription = null)
    Button(onClick = { /*TODO*/ }) {
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTheme {
        Greeting("Android")
    }
}

@Composable
fun Heading(text: String) {
    Text(text = text, fontSize = 25.sp, modifier = Modifier.padding(bottom = 25.dp))
}

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
                    label = {Text("First name")},
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

@Composable
fun UserList(users: SnapshotStateList<User>, userDetailsUser: MutableState<User?>) {
    Column (modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)){
        for (user in users) {
            UserListItem(user = user, users, userDetailsUser)
        }
    }
}

@Composable
fun UserInformation(user: User?) {
    if (user == null) {
        return
    }

    Column {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Column (modifier = Modifier.padding(horizontal = 7.dp)){
                Text(text = user.firstName, fontSize = 20.sp)
                Text(text = user.activeToString(), fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }



}

@Composable
fun UserListItem(user: User, users: SnapshotStateList<User>, userDetailsUser: MutableState<User?>) {
    Column (modifier = Modifier
        .padding(vertical = 5.dp)
        .background(color = Color(red = 240, green = 240, blue = 245))
        .padding(vertical = 35.dp)) {
        Row (modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            UserInformation(user = user)
            TextButton(onClick = { userDetailsUser.value = user }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteUserDialog(deleteUserDialogUser: MutableState<String>, users: SnapshotStateList<User>, userDetailsUser: MutableState<User?>) {
    if (deleteUserDialogUser.value.isEmpty()) {
        return
    }

    AlertDialog(
        icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = null )},
        title = { Text(text = "Delete User")},
        text = { Text(text = "Do you really want to delete this user?")},
        confirmButton = { TextButton(onClick = {
            users.removeAll { currentUser -> currentUser.firstName.equals(deleteUserDialogUser.value) }
            deleteUserDialogUser.value = ""
            userDetailsUser.value = null
        }) {
            Text(text = "Delete")
        }},
        dismissButton = { TextButton(onClick = { deleteUserDialogUser.value = "" }) {
            Text(text = "Cancel")
        }},
        onDismissRequest = { deleteUserDialogUser.value = "" }
    );
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetails(userDetailsUser: MutableState<User?>, deleteUserDialogUser: MutableState<String>) {
    if (userDetailsUser.value == null) {
        return
    }

    ModalBottomSheet(onDismissRequest = { userDetailsUser.value = null }) {
        Column {
            UserInformation(user = userDetailsUser.value)
        }
        Column {
            Button(onClick = { deleteUserDialogUser.value = userDetailsUser.value!!.firstName }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun AddUser(createUserFormVisible: MutableState<Boolean>) {
    FloatingActionButton(onClick = { createUserFormVisible.value = true }, shape = CircleShape) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}