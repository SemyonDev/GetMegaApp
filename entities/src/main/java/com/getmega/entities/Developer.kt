package com.getmega.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Developer(
    @field:Json(name = "href")
    val href: String,
    @field:Json(name = "avatar")
    val avatar: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "type")
    val type: String = "",
    @field:Json(name = "url")
    val url: String = "",
    @field:Json(name = "repo")
    val repo: Repo? = null,
) : Parcelable

