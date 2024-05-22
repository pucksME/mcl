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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

                    Column {
                        Title()
                        CreateUserForm(users)
                        UserList(users)
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
fun CreateUserForm(users: SnapshotStateList<User>) {
    var firstname by remember {
        mutableStateOf("")
    }

    Row (modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        TextField(value = firstname, onValueChange = {value -> firstname = value}, label = {Text("First name")})
        Button(onClick = { users.add(User(firstname, false)) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun UserList(users: SnapshotStateList<User>) {
    Column (modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)){
        for (user in users) {
            UserListItem(user = user, users)
        }
    }
}

@Composable
fun UserListItem(user: User, users: SnapshotStateList<User>) {
    Column (modifier = Modifier.padding(vertical = 5.dp).background(color = Color(red = 240, green = 240, blue = 245)).padding(vertical = 35.dp)) {
        Row (modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
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
            Button(onClick = { users.removeAll {currentUser ->  currentUser.firstName == user.firstName} }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}