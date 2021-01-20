package com.example.weteams.screen.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.weteams.R
import com.google.firebase.auth.FirebaseUser

@Composable
fun DrawerContent(viewModel: MainViewModel) {
    val user = viewModel.user.value
    if (user == null) {
        return
    }

    ScrollableColumn {
        DrawerHeader(user)

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            DrawerGroup(isTop = true)
            DrawerItem(R.drawable.ic_projects_black_24dp, "Project")
            DrawerGroup(text = "No Project")
            DrawerItem(R.drawable.ic_dashboard_black_24dp, "Dashboard")
            DrawerItem(R.drawable.ic_schedule_black_24dp, "Schedule")
            DrawerItem(R.drawable.ic_files_black_24dp, "Files")
            DrawerItem(R.drawable.ic_chat_black_24dp, "Chat")
            DrawerGroup()
            DrawerItem(R.drawable.ic_settings_black_24dp, "Settings")
        }
    }
}

@Composable
fun DrawerHeader(user: FirebaseUser) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp)
    ) {
        Image(
            imageVector = vectorResource(R.drawable.ic_launcher_foreground)
        )

        Text(
            text = user.displayName ?: "unknown",
            color = MaterialTheme.colors.background,
            fontSize = TextUnit.Sp(20)
        )

        Text(
            text = user.email ?: "unknown",
            color = MaterialTheme.colors.background
        )
    }
}

@Composable
fun DrawerGroup(isTop: Boolean = false, text: String? = null) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!isTop) {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }

        if (text != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DrawerItem(@DrawableRes imageRes: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                // TODO
            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = vectorResource(imageRes),
            modifier = Modifier.padding(start = 8.dp, end = 16.dp)
        )

        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
