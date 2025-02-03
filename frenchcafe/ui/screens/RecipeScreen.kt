package com.example.frenchcafe.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frenchcafe.data.Recipe
import com.example.frenchcafe.viewmodel.GroceryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

@Composable
fun RecipeScreen(
    recipe: Recipe,
    onToggleFavorite: () -> Unit,
    groceryViewModel: GroceryViewModel
) {
    // Track which ingredients have been checked.
    val checkedIngredients = remember { mutableStateMapOf<String, Boolean>() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = recipe.title, style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        IconButton(onClick = onToggleFavorite) {
            Icon(
                imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Toggle Favorite"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ingredients:", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        recipe.ingredients.forEach { ingredient ->
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                val checked = checkedIngredients.getOrElse(ingredient) { false }
                Checkbox(checked = checked, onCheckedChange = { newValue ->
                    checkedIngredients[ingredient] = newValue
                    if (newValue) {
                        // Add ingredient to the grocery list.
                        groceryViewModel.addItem(ingredient)
                    }
                })
                Text(text = ingredient)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Steps:", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        recipe.steps.forEachIndexed { index, step ->
            Text(text = "${index + 1}. $step")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Notes:", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text(text = recipe.notes)
    }
}
