package com.plcoding.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoItem
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoViewModel
import com.plcoding.dictionary.feature_settings.presentation.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SettingScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState
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

                Text(viewModel.settings.value.backgroundColor.toString())

            }
        }


    }

}