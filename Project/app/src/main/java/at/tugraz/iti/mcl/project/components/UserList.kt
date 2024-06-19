package at.tugraz.iti.mcl.project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.tugraz.iti.mcl.project.User

@Composable
fun UserList(users: SnapshotStateList<User>, userDetailsUser: MutableState<User?>, searchUsers: String) {
    System.out.println(searchUsers)
    LazyColumn (modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)){
        for (user in if (searchUsers.isEmpty()) users else users.filter { user -> user.firstName.lowercase().startsWith(searchUsers.lowercase())}) {
            item { UserListItem(user = user, userDetailsUser) }
        }
    }
}

@Composable
fun UserListItem(user: User, userDetailsUser: MutableState<User?>) {
    Card(
        modifier = Modifier.padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            UserInformation(user = user)
            TextButton(onClick = { userDetailsUser.value = user }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}