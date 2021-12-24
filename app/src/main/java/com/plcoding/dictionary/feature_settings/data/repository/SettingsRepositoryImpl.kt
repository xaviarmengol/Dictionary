package com.plcoding.dictionary.feature_settings.data.repository

import com.plcoding.dictionary.feature_settings.data.local.SettingsDaoMemory
import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl (
    private val dao : SettingsDaoMemory
    ) : SettingsRepository
{

    override suspend fun getSettings(): Settings {
        return dao.getSettings()
    }

    override suspend fun setSettings(settings: Settings) {
        dao.setSettings(settings)
    }
}