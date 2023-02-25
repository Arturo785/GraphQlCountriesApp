package com.plcoding.graphqlcountriesapp.domain


// we better create our own classes to avoid use the generated ones and create a dependency
interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}