package com.example.frenchcafe.data

enum class RecipeCategory {
    BREAKFAST, LUNCH, DINNER, SNACKS, DESSERTS
}

data class Recipe(
    val id: Int,
    val title: String,
    val category: RecipeCategory,
    val ingredients: List<String>,
    val steps: List<String>,
    val notes: String,
    var isFavorite: Boolean = false
)