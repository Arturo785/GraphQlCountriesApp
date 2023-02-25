package com.plcoding.graphqlcountriesapp.domain

// we could put this class in here if we need every part of the app to use this
// remember every layer can access domain but domain can not access others
data class SimpleCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)