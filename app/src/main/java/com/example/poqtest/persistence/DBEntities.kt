package com.example.poqtest.persistence

import androidx.room.*

@Entity(
    tableName = "repos"

)
 data class DBGitRepository (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "full_name")
    var full_name: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "description")
    var description: String = ""

)



@Entity(tableName = "remote_keys")
 data class RemoteKeys(
    @PrimaryKey
    @ColumnInfo(name = "repoId")
    var repoId: Long ,
    @ColumnInfo(name = "prevKey")
    var prevKey: Int?,
    @ColumnInfo(name = "nextKey")
    var nextKey: Int? )