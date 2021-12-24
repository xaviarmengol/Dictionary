package com.plcoding.dictionary.feature_settings.domain.use_cases

import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.repository.SettingsRepository

class SetSettingsUseCase (
    private val repository: SettingsRepository
) {

    suspend operator fun invoke(settings: Settings) {
        return repository.setSettings(settings)
    }

}