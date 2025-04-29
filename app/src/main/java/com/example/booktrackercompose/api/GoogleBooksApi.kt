package com.example.booktrackercompose.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q", encoded = false) bookName: String,
        @Query("key") apiKey: String
    ): BookResponse
}