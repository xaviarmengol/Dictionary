package com.plcoding.dictionary

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.dictionary.feature_dictionary.presentation.ConsultedWordsViewModel
import com.plcoding.dictionary.ui.components.AppBar
import com.plcoding.dictionary.ui.uievents.GetEventsInScreen

@Composable
fun ConsultedWordsScreen(navController: NavController) {

    val viewModel: ConsultedWordsViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    GetEventsInScreen(
        flow = viewModel.eventFlow,
        scaffoldState = scaffoldState,
        navController = navController
    )


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = "Consulted Words",
                icon = Icons.Default.ArrowBack,
                onClick = { viewModel.onNavigateBack()},
                iconAction1 = Icons.Default.Clear,
                onClickAction1 = { viewModel.onDeleteWordsWithLang()}
            )
        }
    ) {
        LazyColumn(modifier= Modifier.fillMaxSize()) {
            items(state.words) { word ->
                Text("${word.word}")
            }
        }
    }


}