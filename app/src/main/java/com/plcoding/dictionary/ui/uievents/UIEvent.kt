package com.plcoding.dictionary.ui.uievents

sealed class UIEvent {
    data class ShowSnackbar(val message: String) : UIEvent()
    object NavigateBack : UIEvent()
    data class NavigateTo(val destination: String): UIEvent()
}