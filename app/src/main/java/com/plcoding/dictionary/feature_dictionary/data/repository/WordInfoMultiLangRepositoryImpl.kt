package com.plcoding.dictionary.feature_dictionary.data.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDao
import com.plcoding.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoMultiLangRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String, lang: String): Flow<Resource<List<WordInfo>>>  = flow {
        // All data should come from DB. Single source of truth

        emit(Resource.Loading())

        val wordInfos = dao.getWordInfosWithLang(word, lang).map {it.toWordInfo()}
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word = word, lang = lang)

            dao.deleteWordInfos(remoteWordInfos.map{it.word})
            dao.insertWordInfos(remoteWordInfos.map{it.toWordInfosEntity()})

        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Word not found",
                data = wordInfos // From DB
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Could't reach the server",
                data = wordInfos // From DB
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map{it.toWordInfo()}
        emit (Resource.Success(newWordInfos))


    }

}