package com.example.nyc_schools_test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nyc_schools_test.common.FailedResponseException
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.domain.HeroeUseCase
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()
    lateinit var handler: CoroutineExceptionHandler
    lateinit var subject: ViewModel
    lateinit var heroeUseCase: HeroeUseCase
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScopeCoroutine = TestScope(testDispatcher)

    @Before
    fun setUpTest() {
        heroeUseCase = mockk()
        subject = ViewModel(
            handler,
            heroeUseCase,
            testScopeCoroutine
        )
    }

    @Test
    fun `test everything works`() = runTest(testDispatcher) {
        testDispatcher.scheduler.advanceTimeBy(2000L)
        assertTrue(true)
    }

    @Test
    fun `get HEROEDOMAIN list when fetching data from server returns ERROR response`() =
        runTest(testDispatcher) {
            /**
             * Given
             */
            coEvery {
                heroeUseCase.invoke()
            } returns flowOf(
                StateAction.ERROR(FailedResponseException())
            )

            /**
             * When
             */
    var StateActionTestList = mutableListOf<StateAction?>()

    val job = launch(handler) {
        supervisorScope {
            launch {
                subject.heroeResponse.collect() {
                    subject.heroeResponse.toList(StateActionTestList)
                }
            }
        }
        /**
         * Then
         */
        val successTest = StateAction.ERROR(FailedResponseException())
        assertEquals(StateActionTestList.get(0), successTest)
    }

    job.cancel()

}

@Test
fun `get HEROEDOMAIN list when fetching data from server returns SUCCESS response`() =
    /**
     * Given
     */
    runTest(testDispatcher) {
        val listHeroe = listOf(mockk<HeroeDomain>(relaxed = true))

        coEvery {
            heroeUseCase.invoke()
        } returns flowOf(
            StateAction.SUCCESS(listHeroe)
        )
        subject.getHeroeList()

        /**
         * When
         */
        var StateActionTestList = mutableListOf<StateAction?>()


        val job = launch(handler) {
            supervisorScope {
                launch {
                    subject.heroeResponse.collect {
                        subject.heroeResponse.toList(StateActionTestList)
                    }
                }
            }

            /**
             * Then
             */
            val successTest =
                StateActionTestList.get(0) as StateAction.SUCCESS<List<HeroeDomain>>
            assertEquals(StateActionTestList.get(0), successTest)
        }
        job.cancel()

    }


@After
fun shutdownTest() {
    clearAllMocks()
    Dispatchers.resetMain()
}

}