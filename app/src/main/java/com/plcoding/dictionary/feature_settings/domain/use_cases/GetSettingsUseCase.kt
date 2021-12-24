package com.plcoding.dictionary.feature_settings.domain.use_cases

import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.repository.SettingsRepository

class GetSettingsUseCase (
    private val repository: SettingsRepository
) {

    suspend operator fun invoke(): Settings {
        return repository.getSettings()
    }

}