package com.getmega.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LanguageSpoken(
    @field:Json(name = "urlParam")
    val urlParam: String,
    @field:Json(name = "name")
    val name: String,
) : Parcelable
