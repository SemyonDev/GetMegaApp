package com.getmega.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
) : Parcelable


