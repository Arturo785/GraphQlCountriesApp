package com.plcoding.graphqlcountriesapp.domain

class GetCountryUseCase(
    private val countryClient: CountryClient //comes from DI
) {

    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}