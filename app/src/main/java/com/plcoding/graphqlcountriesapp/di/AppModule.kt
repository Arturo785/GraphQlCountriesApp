package com.plcoding.graphqlcountriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.plcoding.graphqlcountriesapp.data.ApolloCountryClient
import com.plcoding.graphqlcountriesapp.domain.CountryClient
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCase
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // gets attached into the singleton instance component
object AppModule {

    // needed for the impl of the client
    @Provides
    @Singleton // lives as long as this component which is the whole app life
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql") // the endpoint
            .build()
    }

    // our impl of the interface client
    @Provides
    @Singleton // lives as long as this component which is the whole app life
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton // lives as long as this component which is the whole app life
    fun provideGetCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton // lives as long as this component which is the whole app life
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }
}