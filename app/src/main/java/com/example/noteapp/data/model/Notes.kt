package com.example.noteapp.data.model

 data class Notes(
     val title: String,
     val description: String
 )

data class NotesResponse(
    val id: String,
    val title: String,
    val description: String,
    val updated_at: String,
    val created_at: String

)

