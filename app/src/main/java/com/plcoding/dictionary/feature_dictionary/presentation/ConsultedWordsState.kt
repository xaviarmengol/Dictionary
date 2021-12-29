package com.plcoding.dictionary.feature_dictionary.presentation

import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

data class ConsultedWordsState(
    val words: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
    val sortAscending: Boolean = false
)