package com.example.poptestluis.persistence

import androidx.room.*

@Entity(
    tableName = "repos"
)
class DBGitRepository {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "full_name")
    var full_name: String = ""


    @ColumnInfo(name = "private_repo")
    var private_repo: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "description")
    var description: String = ""


    constructor() {
        //do nothing
    }

    constructor(
        id: Long,
        full_name: String,
        name: String,
        description: String
    ) {
        this.id = id
        this.full_name = full_name
        this.name = name
        this.description = description
    }
}



@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)