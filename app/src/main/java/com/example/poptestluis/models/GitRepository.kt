package com.example.poptestluis.models


class GitRepository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String
){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitRepository

        if (id != other.id) return false
        if (name != other.name) return false
        if (full_name != other.full_name) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + full_name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}