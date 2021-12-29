package com.plcoding.dictionary.feature_settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import com.plcoding.dictionary.feature_settings.domain.use_cases.SetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
) : ViewModel() {

    private val _settings = mutableStateOf(Settings())
    val settings: State<Settings> = _settings


    init {

        // TODO: Dispatcher should be injected and replaced with -> Dispatchers.IO when used DB of file

        viewModelScope.launch(Dispatchers.Main) {

            try {
                _settings.value = getSettingsUseCase()
            }
            catch (e: Exception) {

            }

        }
    }


    fun setSettings(settings: Settings) {
        viewModelScope.launch(Dispatchers.Main) {
            setSettingsUseCase(settings)
        }
        _settings.value = settings
    }

}