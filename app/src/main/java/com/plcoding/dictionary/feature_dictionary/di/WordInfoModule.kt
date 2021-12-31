package com.plcoding.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.plcoding.dictionary.feature_dictionary.data.local.Converters
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.plcoding.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.plcoding.dictionary.feature_dictionary.data.repository.LocalWordsRepositoryImpl
import com.plcoding.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.plcoding.dictionary.feature_dictionary.data.util.GsonParser
import com.plcoding.dictionary.feature_dictionary.domain.repository.ConsultedWordsRepository
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.DeleteConsultedWordsUseCase
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.GetConsultedWordsUseCase
import com.plcoding.dictionary.feature_dictionary.domain.use_cases.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

   @Provides
   @Singleton
   fun providesGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase {
       return GetWordInfoUseCase(repository)
   }

    @Provides
    @Singleton
    fun providesConsultedWordsUseCase(repository: ConsultedWordsRepository): GetConsultedWordsUseCase {
        return GetConsultedWordsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteConsultedWordsUseCase(repository: ConsultedWordsRepository): DeleteConsultedWordsUseCase {
        return DeleteConsultedWordsUseCase(repository)
    }



    @Provides
    @Singleton
    fun providesWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ) : WordInfoRepository{
        return WordInfoRepositoryImpl(api, db.dao) // Changed from simple to MultiLang implementation
    }

    @Provides
    @Singleton
    fun providesLocalWordsRepository(
        db: WordInfoDatabase
    ) : ConsultedWordsRepository{
        return LocalWordsRepositoryImpl(db.dao) // Changed from simple to MultiLang implementation
    }


    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}