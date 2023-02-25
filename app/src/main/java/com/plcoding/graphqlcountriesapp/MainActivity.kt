package com.plcoding.graphqlcountriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.graphqlcountriesapp.presentation.CountriesScreen
import com.plcoding.graphqlcountriesapp.presentation.CountriesViewModel
import com.plcoding.graphqlcountriesapp.ui.theme.GraphQlCountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint


// A GraphQl schema is as a database schema, it defines the structure and how the data is retrieved
// which data we need and that kind of stuff, different from the restApis which need a way to hold the
// data but we don't define how the data is organized or etc.

//https://www.youtube.com/watch?v=ME3LH2bib3g

//DISCLAIMER, WE USE KOTLIN KTS WHICH IS UNDER KOTLIN AND NOT GROOVY ONE

//DON'T FORGET TO INSTALL GRAPH QL PLUGIN IN ANDROID STUDIO

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQlCountriesAppTheme {
                // we do it like this because of compose, this is how we get viewModels in composables
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()

                // this way we pass the reference of the function to the composable
                CountriesScreen(
                    state = state,
                    onSelectCountry = viewModel::selectCountry,
                    onDismissCountryDialog = viewModel::dismissCountryDialog
                ) // we pass the reference of the function
            }
        }
    }
}