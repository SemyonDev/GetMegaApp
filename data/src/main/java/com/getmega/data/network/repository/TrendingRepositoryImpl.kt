package com.getmega.data.network.repository

import com.getmega.data.network.api.ApiServices
import com.getmega.domain.repository.TrendingRepository
import com.getmega.entities.PeriodSince

class TrendingRepositoryImpl(private val apiServices: ApiServices) :
    TrendingRepository {
    override suspend fun getTrendingRepoList(
        language: String,
        since: PeriodSince,
        spokenLanguageCode: String
    ) = apiServices.getTrendingRepoList(language, since, spokenLanguageCode)

    override suspend fun getTrendingDeveloperList(
        language: String,
        since: PeriodSince
    ) = apiServices.getTrendingDeveloperList(language, since)

    override suspend fun getLanguageList() = apiServices.getLanguageList()

    override suspend fun getSpokenLanguageList() = apiServices.getSpokenLanguageList()


}
