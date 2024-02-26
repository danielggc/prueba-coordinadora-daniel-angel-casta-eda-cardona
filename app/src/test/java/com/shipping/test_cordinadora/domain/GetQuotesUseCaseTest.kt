package com.shipping.test_cordinadora.domain

import com.shipping.test_cordinadora.data.RemissionRepository
import com.shipping.test_cordinadora.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var remissionRepository: RemissionRepository

    lateinit var getQuotesUseCase: GetRemissionModelUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetRemissionModelUseCase(remissionRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { remissionRepository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { remissionRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Quote("Déjame un comentario", "AristiDevs"))
        coEvery { remissionRepository.getAllQuotesFromApi() } returns myList

        //When
        val response = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { remissionRepository.clearQuotes() }
        coVerify(exactly = 1) { remissionRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { remissionRepository.getAllQuotesFromDatabase() }
        assert(response == myList)
    }
}