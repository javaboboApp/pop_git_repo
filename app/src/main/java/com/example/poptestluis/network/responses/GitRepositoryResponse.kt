package com.example.poptestluis.network.responses



import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitRepositoryResponse (

    val id : Int?,
    val name : String?,
    val full_name : String?,
    val description : String?

)





