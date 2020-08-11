package com.example.poptestluis.mappers

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.network.responses.GitRepositoryResponse
import com.example.poptestluis.persistence.DBGitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


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

fun DBGitRepository.asDomainModel(): GitRepository = GitRepository(
    id = id,
    name = name,
    full_name = full_name,
    description = description
)

fun Pager<Int, DBGitRepository>.asDomain(): Flow<PagingData<GitRepository>> {
    return flow.map { it.map {it.asDomainModel()  } }
}



