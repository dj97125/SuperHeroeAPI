package com.example.nyc_schools_test.model.remote.response


import com.google.gson.annotations.SerializedName


data class HeroeResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val imageResponse: ImageResponse?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("powerstats")
    val powerstatsResponse: PowerstatsResponse?,
    @SerializedName("response")
    val response: String,
)


data class ImageResponse(
    @SerializedName("url")
    val url: String
)

data class PowerstatsResponse(
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
    val strength: String
)