package com.getmega.domain.usecase

import com.getmega.entities.Language
import com.getmega.entities.LanguageSpoken
import com.getmega.entities.PeriodSince
import com.getmega.entities.Repo

interface TrendingUseCase {

    suspend fun getTrendingRepoList(
        language: String = "",
        since: PeriodSince = PeriodSince.DAILY,
        spokenLanguageCode: String = ""
    ): List<Repo>

    suspend fun getTrendingDeveloperList(
        language: String = "",
        since: PeriodSince = PeriodSince.DAILY,
    ): List<Repo>

    suspend fun getLanguageList(): List<Language>

    suspend fun getSpokenLanguageList(): List<LanguageSpoken>

}
