package com.plcoding.dictionary.feature_dictionary.domain.use_cases

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase (
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String, lang: String = "en"): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow{ }
        }

        return repository.getWordInfo(word, lang)
    }

}