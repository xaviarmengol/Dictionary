package com.plcoding.dictionary.feature_dictionary.data.remote

import com.plcoding.dictionary.feature_dictionary.data.remote.dto.WordInfosDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/{lang}/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String,
        @Path("lang") lang: String
    ) : List<WordInfosDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}