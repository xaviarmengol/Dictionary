package com.plcoding.dictionary.feature_images.data.repository

import android.util.Log
import com.plcoding.dictionary.feature_images.data.remote.ImagesApi
import com.plcoding.dictionary.feature_images.domain.repository.GetImageRepository

class GetImageRepositoryImpl (
    private val api : ImagesApi
) : GetImageRepository {

    val TAG = "GetImageRepositoryImpl"

    override suspend fun getImageUrl(word: String, lang: String, clientId: String): String {
        var imageUrl = ""
        try {
            val wordImage = api.getWordImages(clientId, word, lang)
            imageUrl = wordImage.getImageUrl()
        } catch (throwable: Throwable) {

            Log.i(TAG, "getImageUrl: Word $word in lang $lang not found")
        }
        Log.i(TAG, "getImageUrl: Word $word in lang $lang -> $imageUrl")
        return imageUrl
    }
}