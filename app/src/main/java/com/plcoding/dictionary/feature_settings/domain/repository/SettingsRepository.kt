package com.plcoding.dictionary.feature_settings.domain.repository

import com.plcoding.dictionary.feature_settings.domain.model.Settings

interface SettingsRepository {

    suspend fun getSettings() : Settings
    suspend fun setSettings(settings: Settings)

}