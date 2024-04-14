package com.example.noteapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.R
import com.example.noteapp.data.model.NotesResponse

@Composable
fun NotesScreen() {

    var  search by remember { mutableStateOf("") }
    var  title  by remember { mutableStateOf("") }
    var  des    by remember { mutableStateOf("") }
    var  isAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                  isAddDialog = true
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
        
        if (isAddDialog){
            
            ShowDialogBox(
                title = title,
                description = des,
                onClose = {isAddDialog = it},
                onClick = {  },
                onTitleChange = {title = it},
                onDescriptionChange = {des = it}
            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogBox(
    title: String,
    description: String,
    onClose :(Boolean) ->Unit,
    onClick : () ->Unit,
    onTitleChange: (String)-> Unit,
    onDescriptionChange:(String)-> Unit
){

    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    AlertDialog(onDismissRequest = { },
        confirmButton = {
               Button(onClick = { onClick() }, modifier = Modifier
                   .fillMaxWidth()
                   .padding(15.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color.Red,
                       contentColor = Color.White
                   ), contentPadding = PaddingValues(15.dp)
               ) {
                        Text(text = "Save")
               }
    },
        shape = RoundedCornerShape(10.dp),
        title = {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd) {

                    IconButton(onClick = {
                        onClose(false)
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "", tint = Color.Red)
                    }
                }
        },
        containerColor = Color.Gray.copy(alpha = 0.5f),
        text = {
            Column(modifier = Modifier.padding(10.dp)) {
                
                DialogTextField(
                    text = title,
                    placeholder = stringResource(R.string.enter_title),
                    onValueChange = onTitleChange,
                    modifier = Modifier.focusRequester(focusRequester))

                Spacer(modifier = Modifier.height(15.dp))

                DialogTextField(
                    text = description,
                    placeholder = stringResource(R.string.enter_description),
                    onValueChange = onDescriptionChange,
                    modifier = Modifier.height(300.dp))

            }
        }
    ) {

    }
    
}