package com.example.nyc_schools_test.model.remote

import com.example.nyc_schools_test.common.*
import com.example.nyc_schools_test.model.local.LocalDataSource
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
    fun heroeCatched(): Flow<StateAction>
}

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {


    override fun heroeCatched(): Flow<StateAction> = flow {
        val connected = InternetCheck()
        val remoteService = remoteDataSource.heroeCatched()
        if (connected.isConnected()) {
            remoteService.collect { stateAction ->
                when (stateAction) {
                    is StateAction.SUCCESS<*> -> {
                        val retrievedHeroes = stateAction.response as List<HeroeDomain>
                        val retrievedMessage = stateAction.message
                        emit(StateAction.SUCCESS(retrievedHeroes,retrievedMessage))
                        localDataSource.insertHeroe(retrievedHeroes).collect()


                    }
                    is StateAction.ERROR -> {
                        emit(StateAction.ERROR(FailedNetworkResponseException()))
                    }
                }
            }
        } else {
            val cache = localDataSource.getAllHeroes()
            cache.collect{ stateAction ->
                when (stateAction) {
                    is StateAction.SUCCESS<*> -> {
                        val retrievedHeroes = stateAction.response as List<HeroeDomain>
                        val retrievedMessage = stateAction.message
                        emit(StateAction.SUCCESS(retrievedHeroes,retrievedMessage))
                    }
                    is StateAction.ERROR -> {
                        emit(StateAction.ERROR(FailedCacheResponseException()))
                    }
                }

            }

        }
    }

}



