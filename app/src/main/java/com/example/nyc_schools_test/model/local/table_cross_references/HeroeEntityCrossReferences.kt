package com.example.nyc_schools_test.model.local.table_cross_references

import androidx.room.Entity

@Entity(primaryKeys = ["id", "combat"])
data class HeroePowerStatCrossRef(
    val id: String,
    val combat: String

)

@Entity(primaryKeys = ["id", "url"])
data class HeroeImageCrossRef(
    val id: String,
    val url: String

)

