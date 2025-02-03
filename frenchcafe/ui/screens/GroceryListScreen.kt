package com.example.frenchcafe.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.frenchcafe.data.GroceryItem
import com.example.frenchcafe.viewmodel.GroceryViewModel
import com.example.frenchcafe.viewmodel.GroceryViewModelFactory
import com.example.frenchcafe.data.GroceryRepository // Import GroceryRepository

@Composable
fun GroceryListScreen(navController: NavController, viewModel: GroceryViewModel) {
    val toBuyItems by viewModel.toBuyItems.collectAsStateWithLifecycle()
    val purchasedItems by viewModel.purchasedItems.collectAsStateWithLifecycle()
    var newItemText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("To Buy", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(toBuyItems) { item ->
                GroceryItemRow(item, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Purchased", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(purchasedItems) { item ->
                GroceryItemRow(item, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newItemText,
            onValueChange = { newItemText = it },
            label = { Text("New Item") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                if (newItemText.isNotBlank()) {
                    viewModel.addItem(newItemText)
                    newItemText = ""
                }
            }) {
                Text("Add Item")
            }
            Button(onClick = { /* Implement bulk removal if needed */ }) {
                Text("Remove Items")
            }
        }
    }
}

@Composable
private fun GroceryItemRow(item: GroceryItem, groceryViewModel: GroceryViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Checkbox(
            checked = item.purchased,
            onCheckedChange = { groceryViewModel.updateItem(item.copy(purchased = !item.purchased)) }
        )
        Text(text = item.name)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { groceryViewModel.removeItem(item) }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}