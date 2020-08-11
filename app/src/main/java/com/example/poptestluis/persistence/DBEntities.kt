package com.example.poptestluis.persistence

import androidx.room.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = DBUser::class,
        parentColumns = arrayOf("username"),
        childColumns = arrayOf("owner_name"),
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["owner_name"])]
)
class DBGitRepository {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "full_name")
    var full_name: String = ""

    @ColumnInfo(name = "owner_name")
    var owner_name: String = ""

    @ColumnInfo(name = "private_repo")
    var private_repo: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "date_modif")
    var date_modif: Long? = 0

    @ColumnInfo(name = "description")
    var description: String = ""


    constructor() {
        //do nothing
    }

    constructor(
        id: Long,
        full_name: String,
        owner_name: String,
        private: String,
        name: String,
        description: String,
        dateMof: Long?
    ) {
        this.id = id
        this.full_name = full_name
        this.owner_name = owner_name
        this.private_repo = private
        this.name = name
        this.description = description
        date_modif = dateMof
    }
}

@Entity
data class DBUser(
    @PrimaryKey()
    val username: String = "",
    val avatar_url: String = "",
    val token: String = ""
)
