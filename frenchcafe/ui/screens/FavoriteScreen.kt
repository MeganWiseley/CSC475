package com.example.frenchcafe.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frenchcafe.ui.components.RecipeListItem
import com.example.frenchcafe.viewmodel.RecipesViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun FavoritesScreen(navController: NavController, recipesViewModel: RecipesViewModel) {
    val favoriteRecipes = recipesViewModel.recipes.collectAsStateWithLifecycle().value.filter { it.isFavorite }
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(favoriteRecipes) { recipe ->
            RecipeListItem(recipe = recipe) {
                navController.navigate("recipe/${recipe.id}")
            }
        }
    }
}
