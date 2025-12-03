package com.runanywhere.startup_hackathon20.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.rights.RightItem
import com.runanywhere.startup_hackathon20.rights.RightsData
import com.runanywhere.startup_hackathon20.util.PdfGenerator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowYourRightsScreen(onBack: () -> Unit) {

    val context = LocalContext.current

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = listOf(
        "All",
        "Police & Arrest",
        "Harassment & Threats",
        "Women’s Rights",
        "Landlord Rights",
        "Workplace Rights",
        "Consumer Rights"
    )

    // SMART FILTER + SEARCH
    val filteredList = RightsData.rightsList.filter { right ->

        val query = searchQuery.trim().lowercase()

        val matchesQuery =
            query.isEmpty()
                    || right.title.lowercase().contains(query)
                    || right.body.lowercase().contains(query)
                    || right.category.lowercase().contains(query)
                    || query.split(" ").any { word ->
                right.title.lowercase().contains(word) ||
                        right.body.lowercase().contains(word) ||
                        right.category.lowercase().contains(word)
            }

        val matchesCategory =
            selectedCategory == "All" || right.category == selectedCategory

        matchesQuery && matchesCategory
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Know Your Rights") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF111111),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // SEARCH BAR
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search rights…") },
                leadingIcon = { Icon(Icons.Filled.Search, null) }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // CATEGORY CHIPS
            FilterRow(
                categories = categories,
                selected = selectedCategory,
                onSelectedChanged = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // RIGHTS LIST
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(filteredList) { right ->
                    RightsCard(right, context)
                }
            }
        }
    }
}

@Composable
fun FilterRow(
    categories: List<String>,
    selected: String,
    onSelectedChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        categories.forEach { category ->
            AssistChip(
                onClick = { onSelectedChanged(category) },
                label = { Text(category) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (selected == category) Color(0xFF0A84FF) else Color(0xFF2E2E2E),
                    labelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun RightsCard(right: RightItem, context: Context) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
    ) {
        Column(Modifier.padding(16.dp)) {

            Text(right.title, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Text(right.body, color = Color(0xFFDDDDDD))

            Spacer(Modifier.height(14.dp))

            // DOWNLOAD PDF BUTTON
            Button(
                onClick = {
                    val file = PdfGenerator.generateRightsPdf(context, right)
                    file?.let {
                        val uri = PdfGenerator.getPdfUri(context, it)
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            setDataAndType(uri, "application/pdf")
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A84FF))
            ) {
                Icon(Icons.Filled.Download, null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Download as PDF", color = Color.White)
            }
        }
    }
}
