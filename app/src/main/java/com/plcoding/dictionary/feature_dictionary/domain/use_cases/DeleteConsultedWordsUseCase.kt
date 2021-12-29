package com.plcoding.dictionary.feature_dictionary.domain.use_cases

import android.util.Log
import com.plcoding.dictionary.core.constants.LANGUAGES
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.ConsultedWordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteConsultedWordsUseCase (

    private val repository : ConsultedWordsRepository

    ) {

    private val TAG : String = "DeleteConsultedWords..."

    suspend operator fun invoke(lang: String) {
        if ( lang !in LANGUAGES) {
            Log.e(TAG, "invoke: Language not recognized = $lang", )
            return
        }
        repository.deleteWords(lang)
    }

}