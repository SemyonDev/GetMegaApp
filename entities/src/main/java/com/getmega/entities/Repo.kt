package com.getmega.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val id: Int, // unique ID need
    @field:Json(name = "author")
    val author: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "avatar")
    val avatar: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "language")
    val language: String,
    @field:Json(name = "languageColor")
    val languageColor: String,
    @field:Json(name = "stars")
    val stars: Int,
    @field:Json(name = "forks")
    val forks: Int,
    @field:Json(name = "currentPeriodStars")
    val currentPeriodStars: Int,
    @field:Json(name = "builtBy")
    val builtBy: List<Developer>,
) : Parcelable


