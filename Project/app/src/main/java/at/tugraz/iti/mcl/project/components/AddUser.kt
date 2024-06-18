package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AddUser(createUserFormVisible: MutableState<Boolean>) {
    FloatingActionButton(onClick = { createUserFormVisible.value = true }, shape = CircleShape) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}