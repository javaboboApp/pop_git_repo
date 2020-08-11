package com.example.poqtest.ui.repos.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.poqtest.mappers.asDatabaseModel
import com.example.poqtest.network.IGitRepoService
import com.example.poqtest.persistence.*
import com.example.poqtest.utils.GITHUB_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

private const val TAG = "PageKeyedRemoteMediator"

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db: AppDatabase,
    private val gitRepoService: IGitRepoService,
    private val userName: String
) : RemoteMediator<Int, DBGitRepository>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DBGitRepository>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                if (remoteKeys != null) {
                    // If the previous key is null, then we can't request more data
                    val prevKey = remoteKeys.prevKey
                    if (prevKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKeys.prevKey
                } else
                    GITHUB_STARTING_PAGE_INDEX

            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if(remoteKeys != null)
                   remoteKeys.nextKey
                else GITHUB_STARTING_PAGE_INDEX
            }
        }!!

        try {

            val apiResponse = gitRepoService.getRepository(userName, page, state.config.pageSize)

            Log.i(TAG, "load: ${apiResponse.size}")
            val repos = apiResponse.asDatabaseModel()
            val endOfPaginationReached = repos.isEmpty()
            db.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao.clearRemoteKeys()
                    db.gitDao.clearRepos()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = repos.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                db.remoteKeysDao.insertAll(keys)
                db.gitDao.insertRespositories(repos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            Log.i(TAG, "load: ${exception.message}")

            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            Log.i(TAG, "load: ${exception.message}")

            return MediatorResult.Error(exception)
        } catch (ex: Exception) {
            return MediatorResult.Error(Throwable("Unknown error"))
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, DBGitRepository>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                db.remoteKeysDao.remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, DBGitRepository>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                db.remoteKeysDao.remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, DBGitRepository>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.remoteKeysDao.remoteKeysRepoId(repoId)
            }
        }
    }

}