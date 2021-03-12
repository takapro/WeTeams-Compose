package com.example.weteams.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weteams.R
import com.example.weteams.screen.Route
import com.example.weteams.screen.RouteGroup
import com.example.weteams.screen.main.MainViewModel
import com.example.weteams.screen.navRoute
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    drawerState: DrawerState,
    navController: NavHostController,
    routeGroups: Array<RouteGroup>
) {
    val mainViewModel = viewModel<MainViewModel>()
    val user = mainViewModel.user.value
    if (user == null) {
        return
    }

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        DrawerHeader(user)

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            routeGroups.forEachIndexed { index, routeGroup ->
                DrawerGroup(isTop = index == 0, text = routeGroup.title)
                for (route in routeGroup.routes) {
                    DrawerItem(
                        route = route,
                        enabled = routeGroup.enabled,
                        selected = false // TODO: route == currentRoute
                    ) {
                        navController.navRoute(route)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                }
            }
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
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )

        Text(
            text = user.displayName ?: "unknown",
            color = MaterialTheme.colors.background,
            fontSize = 20.sp
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
fun DrawerItem(route: Route, enabled: Boolean, selected: Boolean, onSelect: () -> Unit) {
    val color =
        if (selected) {
            MaterialTheme.colors.primary
        } else if (enabled) {
            contentColorFor(MaterialTheme.colors.background)
        } else {
            Color.Gray
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .let {
                if (enabled) {
                    it.clickable { onSelect() }
                } else {
                    it
                }
            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(route.iconRes),
            contentDescription = "",
            modifier = Modifier.padding(start = 8.dp, end = 16.dp),
            colorFilter = ColorFilter.tint(color)
        )

        Text(
            text = route.title,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}
