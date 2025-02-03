package com.example.frenchcafe.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frenchcafe.ui.components.RecipeListItem
import com.example.frenchcafe.viewmodel.RecipesViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.padding


@Composable
fun ProfileScreen(navController: NavController, recipesViewModel: RecipesViewModel) {
    val favoriteRecipes = recipesViewModel.recipes.collectAsStateWithLifecycle().value.filter { it.isFavorite }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("User Name", style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Favorite Recipes", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(favoriteRecipes) { recipe ->
                RecipeListItem(recipe = recipe) {
                    navController.navigate("recipe/${recipe.id}")
                }
            }
        }
    }
}
