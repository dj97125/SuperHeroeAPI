package com.example.nyc_schools_test.model.remote

import com.example.nyc_schools_test.common.FailedResponseException
import com.example.nyc_schools_test.common.InternetCheck
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
    fun heroeCatched(): Flow<StateAction>
}

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository {


    override fun heroeCatched(): Flow<StateAction> = flow {
        val connected = InternetCheck()
        val remoteService = remoteDataSource.heroeCatched()
        if (connected.isConnected()) {
            remoteService.collect { stateAction ->
                when (stateAction) {
                    is StateAction.SUCCESS<*> -> {
                        val retrievedHeroes = stateAction.response as List<HeroeDomain>
                        emit(StateAction.SUCCESS(retrievedHeroes))

                    }
                    is StateAction.ERROR -> {
                        emit(StateAction.ERROR(FailedResponseException()))
                    }
                }
            }
        }
    }
}



