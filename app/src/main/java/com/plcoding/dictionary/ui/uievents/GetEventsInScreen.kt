package com.plcoding.dictionary.ui.uievents

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GetEventsInScreen(flow: SharedFlow<UIEvent>, scaffoldState: ScaffoldState, navController: NavController) {

    LaunchedEffect(key1 = true) {
        flow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowSnackbar  -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UIEvent.NavigateBack -> {
                    navController.popBackStack()
                }
                is UIEvent.NavigateTo -> {
                    navController.navigate(event.destination)
                }
            }
        }
    }

}