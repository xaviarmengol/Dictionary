package com.plcoding.dictionary.feature_images.data.remote.dto

data class WordImagesDto(
    val results: List<ResultDto>,
    val total: Int,
    val total_pages: Int
) {
    fun getImageUrl() : String {
        return results[0].urls.thumb
    }
}