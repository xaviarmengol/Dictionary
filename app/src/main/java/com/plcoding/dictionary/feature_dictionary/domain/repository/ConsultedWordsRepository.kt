package com.plcoding.dictionary.feature_dictionary.domain.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface ConsultedWordsRepository {
    fun getWords(lang: String) : Flow<Resource<List<WordInfo>>>

    suspend fun deleteWords(lang: String)
}