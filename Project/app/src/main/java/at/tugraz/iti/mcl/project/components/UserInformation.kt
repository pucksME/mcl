package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.tugraz.iti.mcl.project.User

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