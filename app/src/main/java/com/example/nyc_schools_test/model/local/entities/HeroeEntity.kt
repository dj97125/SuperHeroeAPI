package com.example.nyc_schools_test.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val imageResponse: ImageEntity?,
    val name: String?,
    val powerstatsResponse: PowerstatsEntity?,
    val response: String,
)

@Entity
data class PowerstatsEntity(
    @PrimaryKey(autoGenerate = false)
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String
)

@Entity
data class BiographyEntity(
    val aliases: List<String>,
    val alignment: String,
    val alterEgos: String,
    val firstAppearance: String,
    @PrimaryKey(autoGenerate = false)
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String
)

