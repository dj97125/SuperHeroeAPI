package com.example.nyc_schools_test.model.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val imageResponse: ImageEntity?,
    val name: String?,
    @Embedded
    val powerstatsResponse: PowerstatsEntity?,
    val response: String,
)


data class PowerstatsEntity(
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

data class ImageEntity(
    val url: String
)


