package com.example.a18prkrylov.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogSearch(dialogState: MutableState<Boolean>, onSubmit: (String) -> Unit) {
    val dialogText = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value = false
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
            }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Введите название города:")
                TextField(value = dialogText.value, onValueChange = {
                    dialogText.value = it
                })
            }
        }
    )
}