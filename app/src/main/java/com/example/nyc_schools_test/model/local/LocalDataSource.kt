//package com.example.nyc_schools_test.model.local
//
//import com.example.nyc_schools_test.common.StateAction
//import com.example.nyc_schools_test.model.local.entities.*
//import com.example.nyc_schools_test.model.local.table_cross_references.HeroeImageCrossRef
//import com.example.nyc_schools_test.model.local.table_cross_references.HeroePowerStatCrossRef
//import com.example.nyc_schools_test.model.remote.responses.*
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import javax.inject.Inject
//
//interface LocalDataSource {
//    fun insertHeroe(heroeEntity: List<HeroeDomain>): Flow<StateAction>
//    fun insertPowerStats(powerStatEntity: List<PowerstatsDomain>): Flow<StateAction>
//    fun insertImage(imageEntity: List<ImageDomain>): Flow<StateAction>
//    fun insertHeroePowerStatCrossRef(
//        heroePowerStatCrossRef: List<HeroePowerStatCrossRef>,
//        heroeEntity: List<HeroeDomain>,
//        powerStatEntity: List<PowerstatsDomain>
//    ): Flow<StateAction>
//
//    fun insertHeroeImageCrossRef(
//        heroeImageCrossRef: List<HeroeImageCrossRef>,
//        imageEntity: List<ImageEntity>,
//        heroeEntity: List<HeroesEntity>
//    ): Flow<StateAction>
//
//    fun getAllHeroeAndPowerStats(id: String): Flow<StateAction>
//    fun getAllPowerStatsAndHeroe(combat: String): Flow<StateAction>
//    fun getAllHeroeAndImage(id: String): Flow<StateAction>
//    fun getAllImageAndHeroe(url: String): Flow<StateAction>
//
//
//}
//
//class LocalDataSourceImpl @Inject constructor(
//    private val heroesDao: HeroesDao
//) : LocalDataSource {
//    override fun insertHeroe(heroeEntity: List<HeroeDomain>): Flow<StateAction> = flow {
//        heroesDao.insertHeroe(heroeEntity.fromDomainHeroesModel())
//    }
//
//    override fun insertPowerStats(powerStatEntity: List<PowerstatsDomain>): Flow<StateAction> =
//        flow {
//            heroesDao.insertPowerStat(powerStatEntity.fromDomainPowerStatsModel())
//        }
//
//    override fun insertImage(imageEntity: List<ImageDomain>): Flow<StateAction> = flow {
//        heroesDao.insertImage(imageEntity.fromDomainImageModel())
//    }
//
//    override fun insertHeroePowerStatCrossRef(heroePowerStatCrossRef: List<HeroePowerStatCrossRef>): Flow<StateAction> =
//        flow {
//            heroesDao.insertHeroePowerStatCrossRef(heroePowerStatCrossRef.fromDomainPowerStatCrossRef())
//        }
//
//    override fun insertHeroeImageCrossRef(
//        heroeImageCrossRef: List<HeroeImageCrossRef>,
//        imageEntity: List<ImageEntity>,
//        heroeEntity: List<HeroesEntity>
//    ): Flow<StateAction> =
//        flow {
//            var heroesImages: MutableList<HeroeImageCrossRef> = mutableListOf()
//            var images: MutableList<ImageEntity> = mutableListOf()
//            var heroes: MutableList<HeroesEntity> = mutableListOf()
//            for (i in 1..2) {
//                val image = heroesDao.getAllImage()
//                val heroe = heroesDao.getAllHeroes()
//                heroe.let { heroeResponse ->
//                    heroes.addAll(heroeResponse)
//                }
//                image.let { imageResponse ->
//                    images.addAll(imageResponse)
//                }
//
//            }
//        }
//    heroesDao.insertHeroeImageCrossRef(heroesImages)
//}
//
//
//override fun getAllHeroeAndPowerStats(id: String): Flow<StateAction> = flow {
//    val cache = heroesDao.getAllHeroeAndPowerStats(id)
//    cache.let {
//        emit(StateAction.SUCCESS())
//    }
//
//}
//
//override fun getAllPowerStatsAndHeroe(combat: String): Flow<StateAction> {
//    TODO("Not yet implemented")
//}
//
//override fun getAllHeroeAndImage(id: String): Flow<StateAction> {
//    TODO("Not yet implemented")
//}
//
//override fun getAllImageAndHeroe(url: String): Flow<StateAction> {
//    TODO("Not yet implemented")
//}
//
//}
//
//private fun List<HeroeImageCrossRef>.fromDomainHeroeImageCrossRef(): List<HeroesEntity> = map {
//    it.fromDomainHeroeImageCrossRef()
//}
//
//
//private fun ImageEntity.toDomainImageModel(): ImageDomain =
//    ImageDomain(url)
//
//private fun PowerstatsEntity.toDomainPowerStatsModel(): PowerstatsDomain =
//    PowerstatsDomain(combat, durability, intelligence, power, speed, strength)
//
//private fun List<HeroesEntity>.toDomainHeroesModel(): List<HeroeDomain> = map {
//    it.toDomainHeroesModel()
//}
//
//private fun List<PowerstatsEntity>.toDomainPowerStatsModel(): List<PowerstatsDomain> = map {
//    it.toDomainPowerStatsModel()
//}
//
//private fun List<ImageEntity>.toDomainImageModel(): List<ImageDomain> = map {
//    it.toDomainImageModel()
//}
//
//private fun HeroesEntity.toDomainHeroesModel(): HeroeDomain =
//    HeroeDomain(
//        id,
//        imageResponse = imageResponse?.toDomainImageModel(),
//        name,
//        powerstatsResponse = powerstatsResponse?.toDomainPowerStatsModel(),
//        response,
//    )
//
//
//private fun List<HeroeDomain>.fromDomainHeroesModel(): List<HeroesEntity> = map {
//    it.fromDomainHeroesModel()
//}
//
//private fun HeroeDomain.fromDomainHeroesModel(): HeroesEntity =
//    HeroesEntity(
//        id,
//        imageResponse = imageResponse?.fromDomainImageModel(),
//        name,
//        powerstatsResponse = powerstatsResponse?.fromDomainPowerStatsModel(),
//        response
//    )
//
//private fun List<PowerstatsDomain>.fromDomainPowerStatsModel(): List<PowerstatsEntity> = map {
//    it.fromDomainPowerStatsModel()
//}
//
//private fun List<ImageDomain>.fromDomainImageModel(): List<ImageEntity> = map {
//    it.fromDomainImageModel()
//}
//
//private fun ImageDomain.fromDomainImageModel(): ImageEntity =
//    ImageEntity(url)
//
//private fun PowerstatsDomain.fromDomainPowerStatsModel(): PowerstatsEntity =
//    PowerstatsEntity(combat, durability, intelligence, power, speed, strength)
//
//
//
//
//
//
//
//
//
//
//
//
//
//
