package com.example.frenchcafe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.frenchcafe.data.Recipe
import com.example.frenchcafe.data.RecipeCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipesViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        // Simulate an API call using hardcoded recipes.
        _recipes.value = listOf(
            Recipe(
                id = 1,
                title = "French Toast",
                category = RecipeCategory.BREAKFAST,
                ingredients = listOf("Bread", "Eggs", "Milk", "Cinnamon"),
                steps = listOf("Mix eggs and milk", "Dip bread", "Cook on skillet"),
                notes = "Delicious with a dusting of powdered sugar."
            ),
            Recipe(
                id = 2,
                title = "Niçoise Salad",
                category = RecipeCategory.LUNCH,
                ingredients = listOf("Tuna", "Eggs", "Green Beans", "Olives"),
                steps = listOf("Boil eggs", "Assemble vegetables and tuna", "Drizzle dressing"),
                notes = "A taste of the French Riviera."
            ),
            Recipe(
                id = 3,
                title = "Coq au Vin",
                category = RecipeCategory.DINNER,
                ingredients = listOf("Chicken", "Red Wine", "Mushrooms", "Onions"),
                steps = listOf("Marinate chicken", "Brown chicken", "Simmer with wine and vegetables"),
                notes = "A classic French dish."
            ),
            Recipe(
                id = 4,
                title = "Crepes",
                category = RecipeCategory.DESSERTS,
                ingredients = listOf("Flour", "Eggs", "Milk", "Butter"),
                steps = listOf("Mix ingredients", "Pour thin layer", "Cook on skillet"),
                notes = "Serve with fruit or chocolate sauce."
            )
            // Add more recipes as needed.
        )
    }

    fun toggleFavorite(recipe: Recipe) {
        recipe.isFavorite = !recipe.isFavorite
        _recipes.value = _recipes.value.map { if (it.id == recipe.id) recipe else it }
    }
}
