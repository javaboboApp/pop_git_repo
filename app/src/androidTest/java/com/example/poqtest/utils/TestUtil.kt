package com.example.poqtest.utils

import com.example.poqtest.models.GitRepository
import com.example.poqtest.network.responses.GitRepositoryResponse


object TestUtil {

    val GIT_REPO_1 = GitRepository(1, "repo with id 1", "It's garbage day tomorrow.", "anything")

    val GIT_REPO_2 = GitRepository(2, "repo with id 2", "It's garbage day tomorrow.", "anything")

    val GIT_REPO_3 = GitRepository(3, "repo with id 3", "It's garbage day tomorrow.", "anything")


    val TEST_LIST_GIT_REPO = listOf(GIT_REPO_1, GIT_REPO_2,GIT_REPO_3)




    val GIT_REPO_RESPONSE = GitRepositoryResponse(
        1,
        "pupil with id 1",
        "It's garbage day tomorrow.",
        "Anything"
    )

    val TEST_LIST_GIT_REPONSE__REPO = listOf(GIT_REPO_RESPONSE)



}