package com.plcoding.dictionary.feature_images.domain.use_cases

import android.util.Log
import com.plcoding.dictionary.core.constants.LANGUAGES
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_images.domain.repository.GetImageRepository
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import kotlinx.coroutines.flow.flow

class GetWordImageUrlUseCase (
    private val repository : GetImageRepository,
    private val getSettings: GetSettingsUseCase
    // TODO: 30/12/2021 Is accessing a Use case from another use case correct?
        ) {

    private val TAG = "GetWordImageUrlUseCase"

    suspend operator fun invoke(word: String, lang: String) : String {

        if (word.isBlank()) {
            Log.i(TAG, "invoke: Empty word = $word", )
            return ""
        }

        if ( lang !in LANGUAGES) {
            Log.e(TAG, "invoke: Language not recognized = $lang", )
            return ""
        }

        val clientId = getSettings().photosApiAccessPrivateKey
        return repository.getImageUrl(word, lang, clientId)
    }

}