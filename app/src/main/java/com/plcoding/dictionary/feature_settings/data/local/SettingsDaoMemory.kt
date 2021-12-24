package com.plcoding.dictionary.feature_settings.data.local

import com.plcoding.dictionary.feature_settings.domain.model.Settings

class SettingsDaoMemory (
    private var settingsMemory : Settings
        ){

    suspend fun getSettings(): Settings {

        return settingsMemory
    }

    suspend fun setSettings(settings: Settings) {

        settingsMemory= settings

    }

}