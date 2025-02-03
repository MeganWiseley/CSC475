package com.example.frenchcafe.ui.screens

import androidx.compose.runtime.setValue

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frenchcafe.data.RecipeCategory
import com.example.frenchcafe.ui.components.RecipeListItem
import com.example.frenchcafe.viewmodel.RecipesViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.fillMaxWidth


@Composable
fun HomeScreen(navController: NavController, recipesViewModel: RecipesViewModel) {
    val recipes by recipesViewModel.recipes.collectAsState()
    var selectedCategory by remember { mutableStateOf(RecipeCategory.BREAKFAST) }
    var showFavoritesOnly by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        // Category selection row.
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            RecipeCategory.values().forEach { category ->
                Button(
                    onClick = { selectedCategory = category },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = category.name)
                }
            }
        }
        // Toggle for showing only favorite recipes.
        Row {
            Text("Show favorites only")
            Switch(checked = showFavoritesOnly, onCheckedChange = { showFavoritesOnly = it })
        }
        // Display list of recipes filtered by category and favorite status.
        LazyColumn {
            items(recipes.filter {
                it.category == selectedCategory && (!showFavoritesOnly || it.isFavorite)
            }) { recipe ->
                RecipeListItem(recipe = recipe) {
                    navController.navigate("recipe/${recipe.id}")
                }
            }
        }
    }
}