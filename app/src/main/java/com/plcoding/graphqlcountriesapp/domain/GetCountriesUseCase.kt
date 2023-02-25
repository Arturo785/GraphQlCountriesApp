package com.plcoding.graphqlcountriesapp.domain

class GetCountriesUseCase(
    private val countryClient: CountryClient // comes from di
) {

    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}