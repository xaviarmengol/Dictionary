package com.plcoding.dictionary.feature_dictionary.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.GetWordInfoUseCase
import com.plcoding.dictionary.feature_images.domain.use_cases.GetWordImageUrlUseCase
import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import com.plcoding.dictionary.ui.uievents.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getWordInfo: GetWordInfoUseCase,
    private val getSettings: GetSettingsUseCase,
    private val getWordImageUrl: GetWordImageUrlUseCase
) : ViewModel() {

    val TAG = "WordInfoViewModel"

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(DictionaryState())
    val state: State<DictionaryState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var settings = Settings()

    init{

        // TODO: Dispatcher should be injected and replaced with -> Dispatchers.IO when used DB of file
        viewModelScope.launch() {
            try {
                settings = getSettings()

            } catch (e: Error) {
                _eventFlow.emit(UIEvent.ShowSnackbar("Error loading settings"))
                Log.e(TAG,"init: Error loading settings: ${e.localizedMessage}",
                )
            }
        }

    }

    fun onChangeQuery(query: String) {
        _searchQuery.value = query
    }


    private var searchJob: Job? = null

    fun onSearch(query: String, delay: Long = 10L) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {

            delay(delay)

            settings = getSettings()

            getWordInfo(query, settings.lang).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )

                        onSearchImage(query)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                result.message ?: "Unknow error"
                            )
                        )

                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )

                    }
                }

            }.launchIn(this)
        }
    }

    fun onSearchImage(query: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                imageUrl = getWordImageUrl(query, settings.lang)
            )
        }
    }

    fun onNavigateBack() {
        viewModelScope.launch {
            _eventFlow.emit(
                UIEvent.NavigateBack
            )
        }
    }

    fun onNavigateTo(destination: String) {
        viewModelScope.launch {
            _eventFlow.emit(
                UIEvent.NavigateTo(destination)
            )
        }
    }


}