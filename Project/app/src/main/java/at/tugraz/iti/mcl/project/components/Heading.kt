package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Heading(text: String) {
    Text(text = text, fontSize = 25.sp, modifier = Modifier.padding(bottom = 25.dp))
}