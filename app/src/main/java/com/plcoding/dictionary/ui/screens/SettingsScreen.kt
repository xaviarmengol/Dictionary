package com.plcoding.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.dictionary.feature_settings.presentation.SettingsViewModel
import com.plcoding.dictionary.ui.components.AppBar



@Composable
fun SettingScreen(navController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = "Settings",
                icon = Icons.Default.ArrowBack,
                onClick = {navController.popBackStack()},
            )
        }
    ) {
        Box(
            modifier = Modifier.background(Color.White)
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = viewModel.settings.value.lang,
                    onValueChange =
                    { value ->
                        val newSettings = viewModel.settings.value.copy(
                            lang = value
                        )
                        viewModel.setSettings(newSettings)
                    },

                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "insert language...")
                    }
                )

                Text(viewModel.settings.value.lang)

                TextField(
                    value = viewModel.settings.value.photosApiAccessPrivateKey,
                    onValueChange =
                    { value ->
                        val newSettings = viewModel.settings.value.copy(
                            photosApiAccessPrivateKey = value
                        )
                        viewModel.setSettings(newSettings)
                    },

                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "insert private key...")
                    }
                )

                Text(viewModel.settings.value.photosApiAccessPrivateKey)

                Text(viewModel.settings.value.backgroundColor.toString())

            }
        }


    }

}