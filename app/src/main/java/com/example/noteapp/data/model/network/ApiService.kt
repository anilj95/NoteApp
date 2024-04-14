package com.example.noteapp.data.model.network

import com.example.noteapp.data.model.Notes
import com.example.noteapp.data.model.NotesResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    companion object{
        const val BASE_URL = "18.235.233.99/api/"
    }

    @GET("todo")
    suspend fun getNotes(): List<Notes>

    @POST("todo/")
    suspend fun addNotes(
        @Body notes: Notes
    ): NotesResponse

    @DELETE("todo/{id}/")
    suspend fun deleteNotes(
        @Path("id") id: Int
    )

    @PUT("todo/{id}/")
    suspend fun updateNotes(
        @Path("id") id : Int,
        @Body notes: Notes
    ) : NotesResponse
}