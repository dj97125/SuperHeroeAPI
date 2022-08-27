package com.example.nyc_schools_test.model.remote.responses

import com.google.gson.annotations.SerializedName


data class HeroeDomain(
    val id: String,
    @SerializedName("image")
    val imageResponse: ImageDomain?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("powerstats")
    val powerstatsResponse: PowerstatsDomain?,
    @SerializedName("response")
    val response: String,
)


data class PowerstatsDomain(
    @SerializedName("combat")
    val combat: String,
    @SerializedName("durability")
    val durability: String,
    @SerializedName("intelligence")
    val intelligence: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("speed")
    val speed: String,
    @SerializedName("strength")
    var strength: String
)

data class ImageDomain(
    @SerializedName("url")
    val url: String
)



