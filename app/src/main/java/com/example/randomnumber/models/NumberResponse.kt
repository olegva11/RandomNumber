package com.example.randomnumber.models

data class NumberResponse(
    val text: String? = null,
    val found: Boolean? = null,
    val number: Int? = null,
    val type: String? = null,
    val date: String? = null
)