package com.example.poqtest.dao

import com.example.poqtest.mappers.asDatabaseModel
import com.example.poqtest.utils.DatabaseTest
import com.example.poqtest.utils.TestUtil
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GitRepoTest : DatabaseTest() {


    @Test
    fun test_repos_are_inserted_not_error() = runBlocking{
        //check that the object inserted is the same that we are retrieving...
        val repos = TestUtil.TEST_LIST_GIT_REPONSE__REPO.asDatabaseModel()
        gitDao.insertRespositories(repos)

        val insertedRepo = gitDao.getAll()[0]

        checkNotNull(insertedRepo)

        assertEquals(repos[0].id, insertedRepo.id)
        assertEquals(repos[0].name, insertedRepo.name)
        assertEquals(repos[0].description, insertedRepo.description)
        assertEquals(repos[0].full_name, insertedRepo.full_name)

    }

    @Test
    fun test_clear_is_working() = runBlocking {
        //clear repo is working??
        val repos = TestUtil.TEST_LIST_GIT_REPONSE__REPO.asDatabaseModel()
        gitDao.insertRespositories(repos)
        gitDao.clearRepos()
        val insertedRepo = gitDao.getAll()

        assertEquals(0, insertedRepo.size)

    }
}