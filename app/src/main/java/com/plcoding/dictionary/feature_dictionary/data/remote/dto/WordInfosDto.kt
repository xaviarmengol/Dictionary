package com.plcoding.dictionary.feature_dictionary.data.remote.dto

import com.plcoding.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfosDto(
    val meanings: List<MeaningDto>,
    val origin: String?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfosEntity(lang: String) : WordInfoEntity {
        return WordInfoEntity(
            lang = lang,
            meanings = meanings.map{it.toMeaning()},
            origin = origin?:"",
            phonetic = phonetic?:"",
            word = word
        )
    }
}