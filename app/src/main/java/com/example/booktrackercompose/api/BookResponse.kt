package com.example.booktrackercompose.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    @SerialName("items") val items: List<BookItem>? = emptyList(), //без эмпти листа при возвращении из api пустого списка, выбрасыавалось исключение, что не давало поймать статус not_found. вот и думай головой когда сериализацию котлиновскую юзаешь
    @SerialName("totalItems") val totalItems: Int?
)