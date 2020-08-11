package com.example.poptestluis.mappers

import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.network.responses.GitRepositoryResponse
import com.example.poptestluis.persistence.DBGitRepository



    fun GitRepositoryResponse.asDomainModel(): GitRepository {
    return GitRepository(
        id = id?.toLong() ?: 0,
        name = name ?: "",
        full_name = full_name ?: "",
        description = description ?: ""
    )
}

fun List<GitRepositoryResponse>.asGitRepositoryModel(): List<GitRepository> {
    return map { it.asDomainModel() }
}

fun List<GitRepositoryResponse>.asDatabaseModel(): List<DBGitRepository> {

    return map {

        DBGitRepository(
            it.id?.toLong() ?: 0,
            it.full_name ?: "",
            it.name ?: "",
            it.description ?: ""
        )
    }
}

fun List<DBGitRepository>.asListDomainModel(): List<GitRepository> {
    val list = map {
        GitRepository(
            id = it.id,
            name = it.name,
            full_name = it.full_name,
            description = it.description
        )
    }
    return list
}


