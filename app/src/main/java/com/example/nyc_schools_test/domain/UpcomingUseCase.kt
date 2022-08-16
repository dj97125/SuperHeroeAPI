package com.example.nyc_schools_test.domain

import com.example.nyc_schools_test.model.remote.Repository
import javax.inject.Inject

class UpcomingUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = repository.UpcomingCatched()
}