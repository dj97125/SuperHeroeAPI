package com.example.nyc_schools_test.common

import com.example.nyc_schools_test.model.remote.responses.HeroeDomain

interface OnHeroeClicked {
    fun heroeClicked(heroe: HeroeDomain)
}