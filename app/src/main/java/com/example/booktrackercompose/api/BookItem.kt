package com.example.booktrackercompose.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookItem(
    @SerialName("volumeInfo") val bookItem: Book
)