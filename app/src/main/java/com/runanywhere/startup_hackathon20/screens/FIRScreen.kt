package com.runanywhere.startup_hackathon20.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FIRScreen(
    onBack: () -> Unit
) {
    var date by remember { mutableStateOf("") }
    var finalOutput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FIR / Complaint Generator") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter Date") },
                placeholder = { Text("DD/MM/YYYY") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    finalOutput = "📝 FIR Generated Successfully!\n\nDate Mentioned: $date"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate FIR")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (finalOutput.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = finalOutput,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
