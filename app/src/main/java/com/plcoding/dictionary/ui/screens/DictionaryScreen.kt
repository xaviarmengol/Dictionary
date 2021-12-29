package com.plcoding.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoItem
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoViewModel
import com.plcoding.dictionary.ui.components.AppBar
import kotlinx.coroutines.flow.collectLatest


@Composable
fun DictionaryScreen(navController: NavController) {
    val viewModel: WordInfoViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    // Configure the UI events listener
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = "Search a word",
                icon = Icons.Default.Home,
                onClick = {},
                iconAction1 = Icons.Default.Settings,
                onClickAction1 = {navController.navigate("SETTINGS_SCREEN")},
                iconAction2 = Icons.Default.ThumbUp,
                onClickAction2 = {navController.navigate("CONSULTED_WORDS_SCREEN")})
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
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier= Modifier.fillMaxSize()
                ) {
                    items(state.wordInfoItems.size) { i ->
                        val wordInfo = state.wordInfoItems[i]

                        if (i>0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        WordInfoItem(wordInfo = wordInfo)
                        if (i < state.wordInfoItems.size-1) {
                            Divider(
                            )
                        }

                    }
                }


            }
        }


    }

}