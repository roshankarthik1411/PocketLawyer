package com.runanywhere.startup_hackathon20.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentCheckerScreen(
    onBack: () -> Unit
) {
    var documentType by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var documentId by remember { mutableStateOf("") }
    var validity by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Document Checker") },
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = documentType,
                onValueChange = { documentType = it },
                label = { Text("Document Type") },
                placeholder = { Text("Aadhar / PAN / Passport") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Full Name") },
                placeholder = { Text("Enter the full name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = documentId,
                onValueChange = { documentId = it },
                label = { Text("Document Number") },
                placeholder = { Text("XXXX-XXXX-XXXX") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = validity,
                onValueChange = { validity = it },
                label = { Text("Validity / Expiry") },
                placeholder = { Text("DD/MM/YYYY or Lifetime") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    result =
                        """
                        🔎 Document Check Result
                        
                        • Type: $documentType
                        • Holder Name: $userName
                        • ID: $documentId
                        • Validity: $validity
                        
                        ✅ Status: Verified (Mock Check)
                        """.trimIndent()
                }
            ) {
                Text("Verify Document")
            }

            if (result.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

