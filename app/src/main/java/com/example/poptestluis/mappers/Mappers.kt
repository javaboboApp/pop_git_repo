package com.example.poptestluis.mappers

import android.net.Uri
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.models.GitUser
import com.example.poptestluis.network.responses.GitRepositoryResponse
import com.example.poptestluis.network.responses.UserResponse
import com.example.poptestluis.persistence.DBGitRepository
import com.example.poptestluis.persistence.DBUser


fun List<DBUser>.asDomainModel(): List<GitUser> {
    return map {
        GitUser(
            name = it.username, photo = Uri.parse(it.avatar_url)
        )
    }

}

fun GitUser.asDbMoodel(): DBUser {
    var avatarUrl = ""
    var tokenAux = ""
    photo?.let { avatarUrl = photo.toString() }
    return DBUser(name, avatarUrl, tokenAux)
}


fun UserResponse.asDomainModel(): GitUser {
    return GitUser(login, Uri.parse(avatar_url))
}


    fun GitRepositoryResponse.asDomainModel(): GitRepository {
    return GitRepository(
        id = id?.toLong() ?: 0,
        name = name ?: "",
        full_name = full_name ?: "",
        owner = owner?.asDomainModel(),
        private = private!!,
        description = description ?: "",
        date = System.currentTimeMillis()
    )
}

fun List<GitRepositoryResponse>.asGitRepositoryModel(): List<GitRepository> {
    return map { it.asDomainModel() }
}

fun List<GitRepositoryResponse>.asDatabaseModel(userName: String): List<DBGitRepository> {

    return map {
        val isPrivate: String = if (it.private == false) "public" else "private"

        DBGitRepository(
            it.id?.toLong() ?: 0,
            it.full_name ?: "",
            userName,
            isPrivate,
            it.name ?: "",
            it.description ?: "",
            System.currentTimeMillis()
        )
    }
}

fun List<DBGitRepository>.asListDomainModel(): List<GitRepository> {
    val list = map {
        GitRepository(
            id = it.id,
            name = it.name,
            full_name = it.full_name,
            private = it.private_repo.equals("private"),
            description = it.description,
            owner = GitUser(it.name),
            date = it.date_modif

        )
    }
    return list
}


