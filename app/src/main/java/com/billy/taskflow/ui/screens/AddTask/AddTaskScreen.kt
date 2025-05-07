package com.billy.taskflow.ui.screens.AddTask

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.billy.taskflow.ui.screens.MainActivity.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onAddTask: (Task) -> Unit,
    onCancel: () -> Unit,
) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (title.text.isNotBlank()) {
                        onAddTask(
                            Task(
                                id = (0..100000).random(),
                                title = title.text.trim(),
                                description = description.text.trim()
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.text.isNotBlank()
            ) {
                Text("Add Task")
            }
        }
    }
}