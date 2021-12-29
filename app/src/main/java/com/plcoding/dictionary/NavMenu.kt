package com.plcoding.dictionary.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.dictionary.ConsultedWordsScreen
import com.plcoding.dictionary.DictionaryScreen
import com.plcoding.dictionary.SettingScreen

@Composable
fun NavScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "DICTIONARY_SCREEN") {

        composable(route = "DICTIONARY_SCREEN") {
            DictionaryScreen(navController)
        }

        composable(route = "SETTINGS_SCREEN") {
            SettingScreen(navController)
        }

        composable(route = "CONSULTED_WORDS_SCREEN") {
            ConsultedWordsScreen(navController)
        }

    }


}