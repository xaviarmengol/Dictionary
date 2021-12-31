package com.plcoding.dictionary.feature_images.data.remote

import com.plcoding.dictionary.feature_dictionary.data.remote.dto.WordInfosDto
import com.plcoding.dictionary.feature_images.data.remote.dto.WordImagesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImagesApi {

    @GET("search/photos")
    suspend fun getWordImages(
        @Query("client_id") clientId: String,
        @Query("query") word: String,
        @Query("lang") lang: String,
        @Query("per_page") perPage: Int = 1
    ) : WordImagesDto

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }
}