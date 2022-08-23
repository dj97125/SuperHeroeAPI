package com.example.nyc_schools_test.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nyc_schools_test.common.FailedResponseException
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.domain.HeroeUseCase
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val handler: CoroutineExceptionHandler,
    private val heroeUseCase: HeroeUseCase,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _heroeResponse: MutableStateFlow<StateAction?> = MutableStateFlow(null)
    val heroeResponse: StateFlow<StateAction?>
        get() = _heroeResponse.asStateFlow()


    var heroe: HeroeDomain? = null

    init {
        getHeroeList()
    }


    fun getHeroeList() {
        coroutineScope.launch(handler) {
            supervisorScope {
                launch {
                    heroeUseCase().collect { stateAction ->
                        when (stateAction) {
                            is StateAction.SUCCESS<*> -> {
                                val retrievedHeroes = stateAction.response as List<HeroeDomain>
                                _heroeResponse.value = StateAction.SUCCESS(retrievedHeroes)
                                _isLoading.value = false
                            }
                            is StateAction.ERROR -> {
                                _heroeResponse.value = StateAction.ERROR(FailedResponseException())
                                _isLoading.value = true
                            }
                        }
                    }
                }
            }
        }
    }
}








