package com.plcoding.dictionary

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.plcoding.dictionary.feature_dictionary.presentation.WordInfoItem
import com.plcoding.dictionary.feature_dictionary.presentation.DictionaryViewModel
import com.plcoding.dictionary.ui.components.AppBar
import com.plcoding.dictionary.ui.components.FindBar
import com.plcoding.dictionary.ui.uievents.GetEventsInScreen


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun DictionaryScreen(navController: NavController) {
    val viewModel: DictionaryViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    // Configure the UI events listener
    GetEventsInScreen(
        flow = viewModel.eventFlow,
        scaffoldState = scaffoldState,
        navController = navController
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = "Search a word",
                icon = Icons.Default.Home,
                onClick = {},
                iconAction1 = Icons.Default.Settings,
                onClickAction1 = {viewModel.onNavigateTo("SETTINGS_SCREEN")},
                iconAction2 = Icons.Default.ThumbUp,
                onClickAction2 = {viewModel.onNavigateTo("CONSULTED_WORDS_SCREEN")})
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

                FindBar(
                    findValue = TextFieldValue(viewModel.searchQuery.value),
                    onValueChange = {viewModel.onChangeQuery(it)},
                    onIconClick = {
                        viewModel.onChangeQuery("")
                    },
                    onKeyboardSearch = {
                        viewModel.onSearch(viewModel.searchQuery.value)
                    },
                    visible = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier= Modifier.fillMaxSize()
                ) {

                    item {
                        Image(
                            painter = rememberImagePainter(
                                data = state.imageUrl,
                            ),
                            contentDescription = "Image description",
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .size(150.dp)
                                .padding(4.dp)
                        )
                    }

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

                Spacer(modifier = Modifier.height(16.dp))

            }
        }


    }

}