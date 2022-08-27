package com.example.nyc_schools_test.model.local

import android.content.Context
import androidx.room.*
import com.example.nyc_schools_test.common.DATABASE_NAME
import com.example.nyc_schools_test.model.local.entities.*


@Dao
interface HeroesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroe(heroeEntity: List<HeroesEntity>)

    @Query("SELECT * FROM HeroesEntity")
    suspend fun getAllHeroes(): List<HeroesEntity>


}


@Database(
    version = 1,
    entities = [HeroesEntity::class],
    exportSchema = false
)
abstract class HeroesDB : RoomDatabase() {
    abstract fun heroesDao(): HeroesDao

    companion object {
        @Volatile
        private var INSTANCE: HeroesDB? = null

        fun getInstance(context: Context): HeroesDB {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HeroesDB::class.java,
                    DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}