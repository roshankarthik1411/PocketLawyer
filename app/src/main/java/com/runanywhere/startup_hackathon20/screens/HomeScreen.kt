package com.runanywhere.startup_hackathon20.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFIR: () -> Unit,
    onDocumentCheck: () -> Unit,
    onRightsInfo: () -> Unit,
    onEvidenceManager: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pocket Lawyer") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Button(
                onClick = onFIR,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📄 FIR / Complaint Generator")
            }

            Button(
                onClick = onDocumentCheck,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📑 Document Checker")
            }

            Button(
                onClick = onRightsInfo,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("⚖️ Know Your Rights")
            }

            Button(
                onClick = onEvidenceManager,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🧾 Evidence Manager")
            }
        }
    }
}
