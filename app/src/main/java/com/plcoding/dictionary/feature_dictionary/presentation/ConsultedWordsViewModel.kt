package com.plcoding.dictionary.feature_dictionary.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.DeleteConsultedWordsUseCase
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.GetConsultedWordsUseCase
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import com.plcoding.dictionary.ui.uievents.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultedWordsViewModel @Inject constructor(
    private val getConsultedWords: GetConsultedWordsUseCase,
    private val deleteConsultedWords: DeleteConsultedWordsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    // TODO: 29/12/2021 Review if it's right to deppend on a usecase of another feature
) : ViewModel() {

    private val _state = mutableStateOf(ConsultedWordsState())
    val state : State<ConsultedWordsState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val TAG = "ConsultedWordsViewModel"

    init{

        // TODO: Dispatcher should be injected and replaced with -> Dispatchers.IO when used DB of file
        viewModelScope.launch() {
            try {
                val settings = getSettingsUseCase()
                onGetConsultedWords(settings.lang)

            } catch (e: Error) {
                _eventFlow.emit(UIEvent.ShowSnackbar("Error loading consulted words"))
                Log.e(TAG,"init: Error loading settings or consulted words: ${e.localizedMessage}",
                )
            }
        }

    }

    private fun onGetConsultedWords (lang: String) {

        getConsultedWords(lang).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        words = emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        words = result.data?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UIEvent.ShowSnackbar(result.message?:"Unknown Error")
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    fun onDeleteWordsWithLang() {

        viewModelScope.launch {
            val settings = getSettingsUseCase()
            deleteConsultedWords(settings.lang)

            onGetConsultedWords(settings.lang)
        }


    }

    fun onNavigateBack() {
        viewModelScope.launch {
            _eventFlow.emit(
                UIEvent.NavigateBack
            )
        }
    }


}