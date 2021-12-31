package com.plcoding.dictionary.feature_settings.di

import androidx.compose.ui.graphics.Color
import com.plcoding.dictionary.feature_settings.data.local.SettingsDaoMemory
import com.plcoding.dictionary.feature_settings.data.repository.SettingsRepositoryImpl
import com.plcoding.dictionary.feature_settings.domain.model.Settings
import com.plcoding.dictionary.feature_settings.domain.repository.SettingsRepository
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import com.plcoding.dictionary.feature_settings.domain.use_cases.SetSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

   @Provides
   @Singleton
   fun providesGetSettings(repository: SettingsRepository): GetSettingsUseCase {
       return GetSettingsUseCase(repository)
   }

    @Provides
    @Singleton
    fun providesSetSettings(repository: SettingsRepository): SetSettingsUseCase {
        return SetSettingsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSettingsRepository(
        dao: SettingsDaoMemory,
    ) : SettingsRepository {
        return SettingsRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providesDaoMemory() : SettingsDaoMemory {
        return SettingsDaoMemory(
            Settings(
                lang = "es",
                backgroundColor = Color(0),
                photosApiAccessPrivateKey = "API_KEY"
            )
        )
    }

}