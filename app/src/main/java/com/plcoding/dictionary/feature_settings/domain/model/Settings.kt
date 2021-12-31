package com.plcoding.dictionary.feature_settings.domain.model

import androidx.compose.ui.graphics.Color

data class Settings(
    val lang: String = "",
    val backgroundColor: Color = Color(0),
    val photosApiAccessPrivateKey: String = "API_PRIVATE_KEY"
)
