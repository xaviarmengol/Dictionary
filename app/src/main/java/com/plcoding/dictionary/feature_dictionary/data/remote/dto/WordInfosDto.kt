package com.plcoding.dictionary.feature_dictionary.data.remote.dto

import com.plcoding.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfosDto(
    val meanings: List<MeaningDto>,
    val origin: String?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfosEntity() : WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map{it.toMeaning()},
            origin = origin?:"",
            phonetic = phonetic?:"",
            word = word
        )
    }
}