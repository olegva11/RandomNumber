package com.example.randomnumber.models


data class NumberResponse(
    val text: String? = null,
    val found: Boolean? = null,
    val number: Long? = null,
    val type: String? = null,
    val date: String? = null
)