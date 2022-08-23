//package com.example.nyc_schools_test.model.local
//
//import android.content.Context
//import androidx.room.*
//import com.example.nyc_schools_test.common.DATABASE_NAME
//import com.example.nyc_schools_test.model.local.entities.*
//import com.example.nyc_schools_test.model.local.table_cross_references.HeroeImageCrossRef
//import com.example.nyc_schools_test.model.local.table_cross_references.HeroePowerStatCrossRef
//
//
//@Dao
//interface HeroesDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHeroe(heroeEntity: List<HeroesEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertPowerStat(powerstatsEntity: List<PowerstatsEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertImage(imageEntity: List<ImageEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHeroePowerStatCrossRef(heroePowerStatCrossRef: List<HeroePowerStatCrossRef>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertHeroeImageCrossRef(heroeImageCrossRef: List<HeroeImageCrossRef>)
//
//    @Transaction
//    @Query("SELECT * FROM HeroesEntity where id = :id")
//    suspend fun getAllHeroeAndPowerStats(id: String): List<HeroeAndPowerstats>
//
//    @Transaction
//    @Query("SELECT * FROM PowerstatsEntity where combat = :combat")
//    suspend fun getAllPowerStatsAndHeroe(combat: String): List<PowerstatsAndHeroe>
//
//    @Transaction
//    @Query("SELECT * FROM HeroesEntity where id = :id")
//    suspend fun getAllHeroeAndImage(id: String): List<HeroeAndImage>
//
//    @Transaction
//    @Query("SELECT * FROM ImageEntity where url = :url")
//    suspend fun getAllImageAndHeroe(url: String): List<ImageAndHeroe>
//
//    @Transaction
//    @Query("SELECT * FROM ImageEntity")
//    suspend fun getAllImage(): List<ImageEntity>
//
//    @Transaction
//    @Query("SELECT * FROM HeroesEntity")
//    suspend fun getAllHeroes(): List<HeroesEntity>
//
//    @Transaction
//    @Query("SELECT * FROM PowerstatsEntity")
//    suspend fun getAllPowerStats(): List<PowerstatsEntity>
//
//
//}
//
//
//@Database(
//    version = 1,
//    entities = [HeroesEntity::class,
//        PowerstatsEntity::class,
//        ImageEntity::class,
//        HeroeImageCrossRef::class,
//        HeroePowerStatCrossRef::class],
//    exportSchema = false
//)
//abstract class HeroesDB : RoomDatabase() {
//    abstract fun heroesDao(): HeroesDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: HeroesDB? = null
//
//        fun getInstance(context: Context): HeroesDB {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    HeroesDB::class.java,
//                    DATABASE_NAME
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
//}