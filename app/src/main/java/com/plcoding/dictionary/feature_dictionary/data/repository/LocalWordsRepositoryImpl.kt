package com.plcoding.dictionary.feature_dictionary.data.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDao
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.ConsultedWordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalWordsRepositoryImpl (
    private val dao : WordInfoDao
) : ConsultedWordsRepository {

    override fun getWords(lang: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val allWords = dao.getAllWordInfosWithLang(lang).map {it.toWordInfo()}
        emit (Resource.Success(allWords))
    }

    override suspend fun deleteWords(lang: String) {
            dao.deleteAllWordsWithLang(lang)
    }
}