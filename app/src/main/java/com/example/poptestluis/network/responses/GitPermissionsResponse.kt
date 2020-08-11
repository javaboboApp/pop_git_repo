package com.example.poptestluis.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitPermissionsResponse (

    val admin : Boolean?,
    val push : Boolean?,
    val pull : Boolean?
)