package com.example.booktrackercompose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("title") val title: String? = "",
    @SerialName("authors") val authors: List<String>? = emptyList(),
    @SerialName("publishedDate") val publishedDate: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("imageLinks") val imageLinks: ImageLinks? = ImageLinks(),
    @SerialName("pageCount") val pageCount: Int? = 0
)

@Serializable
data class BookItem(
    @SerialName("volumeInfo") val bookItem: Book
)

@Serializable
data class BookResponse(
    @SerialName("items") val items: List<BookItem>
)

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail") val badImage: String? = "",
    @SerialName("thumbnail") val maybeNormImage: String? = ""
)