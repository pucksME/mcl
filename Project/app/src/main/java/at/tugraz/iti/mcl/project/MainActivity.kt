package at.tugraz.iti.mcl.project

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.tugraz.iti.mcl.project.components.AddUser
import at.tugraz.iti.mcl.project.components.AuthenticateUserDialog
import at.tugraz.iti.mcl.project.components.CreateUserForm
import at.tugraz.iti.mcl.project.components.DeleteUserDialog
import at.tugraz.iti.mcl.project.components.RecognizeUserDialog
import at.tugraz.iti.mcl.project.components.UserDetails
import at.tugraz.iti.mcl.project.components.UserList
import at.tugraz.iti.mcl.project.ui.theme.ProjectTheme

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

                    val recognizeUserDialogVisible = remember {
                        mutableStateOf(false)
                    }

                    val authenticateUserDialogUser = remember {
                        mutableStateOf<User?>(null)
                    }

                    val sensorData = remember {
                        mutableStateListOf<Array<Float>>()
                    }

                    initSensor(sensorData)

                    Scaffold (
                        topBar = { TopAppBar(
                            title = { Text(text = "User Profiles")},
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
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
                            RecognizeUserDialog(recognizeUserDialogVisible = recognizeUserDialogVisible, sensorData)
                            AuthenticateUserDialog(authenticateUserDialogUser = authenticateUserDialogUser, sensorData)
                            UserDetails(userDetailsUser, deleteUserDialogUser, authenticateUserDialogUser)
                            CreateUserForm(users, createUserFormVisible)
                            UserList(users, userDetailsUser)
                        }
                    }
                }
            }
        }
    }

    private fun initSensor(sensorData: SnapshotStateList<Array<Float>>) {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        sensorManager.registerListener(AccelerationSensorEventListener(sensorData), sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
}