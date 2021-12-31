package com.plcoding.dictionary.feature_images.domain.repository

interface GetImageRepository {

    suspend fun getImageUrl(word: String, lang: String, clientId: String) : String

}