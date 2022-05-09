package com.getmega.data.network.api


import com.getmega.entities.Language
import com.getmega.entities.LanguageSpoken
import com.getmega.entities.PeriodSince
import com.getmega.entities.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/repositories")
    suspend fun getTrendingRepoList(
        @Query("language") language: String,
        @Query("since") since: PeriodSince = PeriodSince.DAILY,
        @Query("spoken_language_code") spokenLanguageCode: String
    ): List<Repo>

    @GET("/developers")
    suspend fun getTrendingDeveloperList(
        @Query("language") language: String,
        @Query("since") since: PeriodSince = PeriodSince.DAILY,
    ): List<Repo>

    @GET("/languages")
    suspend fun getLanguageList(): List<Language>

    @GET("/spoken_languages")
    suspend fun getSpokenLanguageList(): List<LanguageSpoken>

}

