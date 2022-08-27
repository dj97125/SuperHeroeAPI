package com.example.nyc_schools_test.model.local

import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.model.local.entities.HeroesEntity
import com.example.nyc_schools_test.model.local.entities.ImageEntity
import com.example.nyc_schools_test.model.local.entities.PowerstatsEntity
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import com.example.nyc_schools_test.model.remote.responses.ImageDomain
import com.example.nyc_schools_test.model.remote.responses.PowerstatsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LocalDataSource {
    fun insertHeroe(heroeEntity: List<HeroeDomain>): Flow<StateAction>
    fun getAllHeroes(): Flow<StateAction>


}

class LocalDataSourceImpl @Inject constructor(
    private val heroesDao: HeroesDao
) : LocalDataSource {
    override fun insertHeroe(heroeEntity: List<HeroeDomain>): Flow<StateAction> = flow {
        heroesDao.insertHeroe(heroeEntity.fromDomainHeroesModel())
    }

    override fun getAllHeroes(): Flow<StateAction> = flow {
        val cache = heroesDao.getAllHeroes()
        cache.let {
            emit(StateAction.SUCCESS(it.toDomainHeroesModel(),"Data From Cache..."))
        }
    }
}


private fun HeroesEntity.toDomainHeroesModel(): HeroeDomain =
    HeroeDomain(
        id,
        imageResponse = imageResponse?.toDomainImageModel(),
        name,
        powerstatsResponse = powerstatsResponse?.toDomainPowerStatsModel(),
        response
    )

private fun List<HeroesEntity>.toDomainHeroesModel(): List<HeroeDomain> = map {
    it.toDomainHeroesModel()
}

private fun List<PowerstatsEntity>.toDomainPowerStatsModel(): List<PowerstatsDomain> = map {
    it.toDomainPowerStatsModel()
}

private fun List<ImageEntity>.toDomainImageModel(): List<ImageDomain> = map {
    it.toDomainImageModel()
}

private fun List<HeroeDomain>.fromDomainHeroesModel(): List<HeroesEntity> = map {
    it.fromDomainHeroesModel()
}


private fun ImageEntity.toDomainImageModel(): ImageDomain =
    ImageDomain(url)

private fun PowerstatsEntity.toDomainPowerStatsModel(): PowerstatsDomain =
    PowerstatsDomain(combat, durability, intelligence, power, speed, strength)


private fun HeroeDomain.fromDomainHeroesModel(): HeroesEntity =
    HeroesEntity(
        id,
        imageResponse = imageResponse?.fromDomainImageModel(),
        name,
        powerstatsResponse = powerstatsResponse?.fromDomainPowerStatsModel(),
        response
    )

private fun List<PowerstatsDomain>.fromDomainPowerStatsModel(): List<PowerstatsEntity> = map {
    it.fromDomainPowerStatsModel()
}

private fun List<ImageDomain>.fromDomainImageModel(): List<ImageEntity> = map {
    it.fromDomainImageModel()
}

private fun ImageDomain.fromDomainImageModel(): ImageEntity =
    ImageEntity(url)

private fun PowerstatsDomain.fromDomainPowerStatsModel(): PowerstatsEntity =
    PowerstatsEntity(combat, durability, intelligence, power, speed, strength)














