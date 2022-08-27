package com.example.nyc_schools_test.model.remote

import com.example.nyc_schools_test.common.FailedNetworkResponseException
import com.example.nyc_schools_test.common.NullResponseException
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.model.remote.response.HeroeResponse
import com.example.nyc_schools_test.model.remote.response.ImageResponse
import com.example.nyc_schools_test.model.remote.response.PowerstatsResponse
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import com.example.nyc_schools_test.model.remote.responses.ImageDomain
import com.example.nyc_schools_test.model.remote.responses.PowerstatsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface RemoteDataSource {
    fun heroeCatched(): Flow<StateAction>
}

class RemoteDataSourceImpl @Inject constructor(
    private val service: RemoteApi
) : RemoteDataSource {

    override fun heroeCatched(): Flow<StateAction> = flow {
        var heroes: MutableList<HeroeResponse> = mutableListOf()
        for (i in 1..50) {
            val respose = service.getHeroeList(i.toString())
            if (respose.isSuccessful) {
                respose.body()?.let { response ->
                    heroes.add(response)
                } ?: throw NullResponseException()
            } else {
                throw FailedNetworkResponseException()
            }
        }
        emit(StateAction.SUCCESS(heroes.toDomainHeroeModel(),"Data From Network..."))
    }

}


private fun List<HeroeResponse>.toDomainHeroeModel(): List<HeroeDomain> = map {
    it.toDomainHeroeModel()
}


private fun ImageResponse.toDomainImageModel(): ImageDomain =
    ImageDomain(url)

private fun PowerstatsResponse.toDomainPowerStatsModel(): PowerstatsDomain =
    PowerstatsDomain(combat, durability, intelligence, power, speed, strength)


private fun HeroeResponse.toDomainHeroeModel(): HeroeDomain =
    HeroeDomain(
        id,
        imageResponse = imageResponse?.toDomainImageModel(),
        name,
        powerstatsResponse = powerstatsResponse?.toDomainPowerStatsModel(),
        response,
    )



