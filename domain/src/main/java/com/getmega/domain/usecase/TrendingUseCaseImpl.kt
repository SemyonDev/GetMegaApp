package com.getmega.domain.usecase


import com.getmega.domain.repository.TrendingRepository
import com.getmega.entities.PeriodSince

class TrendingUseCaseImpl(private val trendingRepository: TrendingRepository) :
    TrendingUseCase {
    override suspend fun getTrendingRepoList(
        language: String,
        since: PeriodSince,
        spokenLanguageCode: String
    ) = trendingRepository.getTrendingRepoList(language, since, spokenLanguageCode)

    override suspend fun getTrendingDeveloperList(
        language: String,
        since: PeriodSince
    ) = trendingRepository.getTrendingDeveloperList(language, since)

    override suspend fun getLanguageList() = trendingRepository.getLanguageList()

    override suspend fun getSpokenLanguageList() = trendingRepository.getSpokenLanguageList()

}
