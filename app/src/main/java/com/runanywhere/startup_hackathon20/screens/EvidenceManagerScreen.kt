package com.runanywhere.startup_hackathon20.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Simple data holder for one evidence note
data class EvidenceItem(
    val text: String,
    val createdAt: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvidenceManagerScreen(
    onBack: () -> Unit
) {
    var evidenceText by remember { mutableStateOf("") }
    val evidenceList = remember { mutableStateListOf<EvidenceItem>() }

    fun addEvidence() {
        val cleaned = evidenceText.trim()
        if (cleaned.isNotEmpty()) {
            val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val timestamp = formatter.format(Date())

            // Add newest evidence at the top
            evidenceList.add(
                0,
                EvidenceItem(
                    text = cleaned,
                    createdAt = timestamp
                )
            )
            evidenceText = ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Evidence Manager") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { addEvidence() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Evidence"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // -------- Input box --------
            OutlinedTextField(
                value = evidenceText,
                onValueChange = { evidenceText = it },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Describe the incident, note, or proof...") },
                placeholder = { Text("Example: \"Argument with landlord on 02 Dec\"") },
                singleLine = false,
                minLines = 2,
                maxLines = 4,
                trailingIcon = {
                    IconButton(onClick = { addEvidence() }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Save Evidence"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Saved Evidence:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (evidenceList.isEmpty()) {
                Text(
                    text = "No evidence added yet.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = evidenceList,
                        key = { it.hashCode() }
                    ) { item ->
                        EvidenceCard(
                            evidence = item,
                            onDelete = { evidenceList.remove(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EvidenceCard(
    evidence: EvidenceItem,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = evidence.text,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Added on: ${evidence.createdAt}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Evidence"
                    )
                }
            }
        }
    }
}
