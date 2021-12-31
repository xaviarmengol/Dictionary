package com.plcoding.dictionary.feature_images.di


import com.plcoding.dictionary.feature_images.data.remote.ImagesApi
import com.plcoding.dictionary.feature_images.data.repository.GetImageRepositoryImpl
import com.plcoding.dictionary.feature_images.domain.repository.GetImageRepository
import com.plcoding.dictionary.feature_images.domain.use_cases.GetWordImageUrlUseCase
import com.plcoding.dictionary.feature_settings.domain.use_cases.GetSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object WordImagesModule {

   @Provides
   @Singleton
   fun providesGetWordImageUrlUseCase(repository: GetImageRepository, getSettingsUseCase: GetSettingsUseCase): GetWordImageUrlUseCase {
       return GetWordImageUrlUseCase(repository, getSettingsUseCase)
   }


    @Provides
    @Singleton
    fun providesGetImageRepository(
        api: ImagesApi
    ) : GetImageRepository{
        return GetImageRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun providesImageApi(): ImagesApi {


        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        okHttpClientBuilder.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(ImagesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
            .create(ImagesApi::class.java)
    }

}