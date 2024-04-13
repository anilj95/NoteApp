package com.example.noteapp.composables

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.data.model.NotesResponse

@Composable
fun NotesScreen() {

    var  search by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

            },contentColor = Color.Red) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
           AppBarSearch(search = search, onValueChange = {
               search = it
           }, modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 50.dp))

        }
    }

}
@Composable
fun AppBarSearch(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit

) {
    TextField(value = search, onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.White
        ), leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = Color.Red)
        }, trailingIcon = {
            if (search.isNotEmpty()){
                IconButton(onClick = {
                    onValueChange("")
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "", tint = Color.Red)

                }
            }

        }, placeholder = Text(text = "search input..", style = TextStyle(
            color = Color.Black.copy(alpha = 0.5f)
        ))

    )

}

@Composable
fun NotesEachRow(
    notesResponse: NotesResponse,
    modifier: Modifier,
    onDelete :()-> Unit
){
    Box(
        modifier
            .fillMaxWidth()
            .background(
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )) {

        Column(modifier = Modifier.padding(15.dp)) {
            Row {
                Text(text = notesResponse.title, style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W600
                ),modifier = Modifier.weight(0.7f))

                IconButton(onClick = {  },modifier = Modifier.weight(0.3f)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "", tint = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = notesResponse.description, style = TextStyle(
                    color = Color.Black.copy(alpha = 0.6f),
                    fontSize = 14.sp,
                    ))
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = notesResponse.updated_at.split("T")[0], style = TextStyle(
                    color = Color.Black.copy(0.3f),
                    fontSize = 10.sp
                ))
            }
        }

    }

}

@Composable
fun DialogTextField(
    text : String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
){
    TextField(value = text, onValueChange = onValueChange, modifier
        .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent

        ), placeholder = {
            Text(text = placeholder, color = Color.Black.copy(0.4f))

        })

}