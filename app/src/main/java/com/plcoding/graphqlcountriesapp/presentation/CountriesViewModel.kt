package com.plcoding.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCase
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCase
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    // with this state flow is like liveData (holds the last state retrieved) and returns it as
    // state to compose components
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()


    // with copy we maintain the other unaffected data and update the one we want
    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            // execute this whenever the getCountriesUseCase.execute() finishes
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(), // when finishes the update happens
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            // execute this whenever the getCountryUseCase.execute(code) finishes
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code) // when finishes the update happens
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update {
            it.copy(
                selectedCountry = null
            )
        }
    }

    // the holder class for this stateFlow used
    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}