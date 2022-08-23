package com.example.nyc_schools_test.model.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nyc_schools_test.model.local.table_cross_references.HeroeImageCrossRef
import com.example.nyc_schools_test.model.local.table_cross_references.HeroePowerStatCrossRef


data class HeroeAndPowerstats(
    @Embedded val heroe: HeroesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "combat",
        associateBy = Junction(HeroePowerStatCrossRef::class)
    )
    val powerstats: List<PowerstatsEntity>?,
)


data class PowerstatsAndHeroe(
    @Embedded val powerHeroe: PowerstatsEntity,
    @Relation(
        parentColumn = "combat",
        entityColumn = "id",
        associateBy = Junction(HeroePowerStatCrossRef::class)
    )

    val heroe: List<HeroesEntity>
)

data class HeroeAndImage(
    @Embedded val heroe: HeroesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "url",
        associateBy = Junction(HeroeImageCrossRef::class)
    )
    val image: List<ImageEntity>
)

data class ImageAndHeroe(
    @Embedded val Image: ImageEntity,
    @Relation(
        parentColumn = "url",
        entityColumn = "id",
        associateBy = Junction(HeroeImageCrossRef::class)
    )
    val heroe: List<HeroesEntity>
)


