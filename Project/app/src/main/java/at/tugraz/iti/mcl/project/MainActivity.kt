package at.tugraz.iti.mcl.project

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import at.tugraz.iti.mcl.project.components.AddUser
import at.tugraz.iti.mcl.project.components.AuthenticateUserDialog
import at.tugraz.iti.mcl.project.components.CreateUserForm
import at.tugraz.iti.mcl.project.components.DeleteUserDialog
import at.tugraz.iti.mcl.project.components.LearnUserDialog
import at.tugraz.iti.mcl.project.components.RecognizeUserDialog
import at.tugraz.iti.mcl.project.components.UserDetails
import at.tugraz.iti.mcl.project.components.UserList
import at.tugraz.iti.mcl.project.ui.theme.ProjectTheme
import org.tensorflow.lite.InterpreterApi
import java.io.File

val data: ArrayList<Array<Float>> = ArrayList()

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val users = remember {
                        mutableStateListOf(
                            User("User 1", true),
                            User("User 2", false),
                            User("User 3", false),
                            User("User 4", false),
                            User("User 5", false),
                            User("User 6", false),
                            User("User 7", false),
                            User("User 8", false),
                        )
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

                    val recognizeUserDialogVisible = remember {
                        mutableStateOf(false)
                    }

                    val authenticateUserDialogUser = remember {
                        mutableStateOf<User?>(null)
                    }

                    val learnUserDialogUser = remember {
                        mutableStateOf<User?>(null)
                    }

                    val sensorData = remember {
                        mutableStateListOf<Array<Float>>()
                    }

                    val recognizedUser = remember {
                        mutableStateOf<User?>(null)
                    }

                    var searchUsers by remember {
                        mutableStateOf("")
                    }

                    initSensor(sensorData, recognizedUser, recognizeUserDialogVisible)

                    Scaffold (
                        topBar = { TopAppBar(
                            title = {
                                //Text(text = "User Profiles")
                                TextField(
                                    value = searchUsers,
                                    onValueChange = { value -> searchUsers = value },
                                    singleLine = true,
                                    label = { Text( text = "Search User")},
                                    modifier = Modifier.background(Color.Transparent),
                                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent)
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            ),
                            actions = {
                                TextButton(onClick = { recognizeUserDialogVisible.value = true }) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                                        Text(text = "Recognize", modifier = Modifier.padding(start = 5.dp))
                                    }
                                }
                            }
                        )},
                        floatingActionButton = { AddUser(createUserFormVisible) }
                    ){ innerPadding ->
                        Column (modifier = Modifier.padding(innerPadding)){
                            DeleteUserDialog(deleteUserDialogUser, users, userDetailsUser)
                            RecognizeUserDialog(recognizeUserDialogVisible, sensorData, recognizedUser)
                            AuthenticateUserDialog(authenticateUserDialogUser, sensorData)
                            LearnUserDialog(learnUserDialogUser, sensorData)
                            UserDetails(
                                userDetailsUser,
                                deleteUserDialogUser,
                                authenticateUserDialogUser,
                                learnUserDialogUser
                            )
                            CreateUserForm(users, createUserFormVisible)
                            UserList(users, userDetailsUser, searchUsers)
                        }
                    }
                }
            }
        }
    }

    private fun initSensor(sensorData: SnapshotStateList<Array<Float>>, recognizedUser: MutableState<User?>, recognizeUserDialogVisible: MutableState<Boolean>) {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        sensorManager.registerListener(AccelerationSensorEventListener(data, sensorData, recognizedUser, recognizeUserDialogVisible), sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun tensorflow(sensorData: SnapshotStateList<Array<Float>>) {
        val interpreter = InterpreterApi.create(File(""), InterpreterApi.Options())
    }
}

fun handleLearUserDone() {
    data.clear()
}

fun handleRecognizeUserDone(recognizedUser: MutableState<User?>) {
    System.out.println(data.size)
    recognizedUser.value = User("User 1", false)
    data.clear()
}
