package com.plcoding.dictionary.feature_dictionary.domain.use_cases

import android.util.Log
import com.plcoding.dictionary.core.constants.LANGUAGES
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase (
    private val repository: WordInfoRepository
) {

    private val TAG : String = "GetConsultedWordUseCase"

    operator fun invoke(word: String, lang: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            Log.i(TAG, "invoke: Empty word = $word", )
            return flow{}
        }

        if ( lang !in LANGUAGES) {
            Log.e(TAG, "invoke: Language not recognized = $lang", )
            return flow{emit(Resource.Error("Language $lang not supported"))}
        }

        return repository.getWordInfo(word, lang)
    }

}