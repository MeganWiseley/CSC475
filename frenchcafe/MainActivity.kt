package com.example.frenchcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.frenchcafe.data.AppDatabase
import com.example.frenchcafe.data.GroceryRepository
import com.example.frenchcafe.ui.components.AppBottomBar
import com.example.frenchcafe.ui.components.AppTopBar
import com.example.frenchcafe.ui.screens.*
import com.example.frenchcafe.ui.theme.FrenchCafeTheme
import com.example.frenchcafe.viewmodel.GroceryViewModel
import com.example.frenchcafe.viewmodel.GroceryViewModelFactory
import com.example.frenchcafe.viewmodel.RecipesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrenchCafeTheme {
                val recipesViewModel: RecipesViewModel = viewModel()

                // Initialize Room and Repository HERE (in MainActivity):
                val db = AppDatabase.getDatabase(applicationContext)
                val groceryDao = db.groceryDao()
                val repository = GroceryRepository(groceryDao)

                // Create the ViewModelFactory HERE (in MainActivity):
                val factory = GroceryViewModelFactory(repository)

                val navController = rememberNavController()
                Scaffold(
                    topBar = { AppTopBar(navController) },
                    bottomBar = { AppBottomBar(navController) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("home") {
                            HomeScreen(navController, recipesViewModel)
                        }
                        composable("favorites") {
                            FavoritesScreen(navController, recipesViewModel)
                        }
                        composable("grocery") {
                            // Get the ViewModel using the factory
                            val viewModel: GroceryViewModel = viewModel(factory = factory)
                            GroceryListScreen(navController, viewModel) // Pass the viewModel
                        }
                        composable("profile") {
                            ProfileScreen(navController, recipesViewModel)
                        }
                        composable(
                            "recipe/{recipeId}",
                            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                            val recipe = recipesViewModel.recipes.collectAsState().value.find { it.id == recipeId }
                            if (recipe != null) {
                                RecipeScreen(
                                    recipe = recipe,
                                    onToggleFavorite = { recipesViewModel.toggleFavorite(recipe) },
                                    groceryViewModel = viewModel(factory = factory)
                                )
                            } else {
                                Text("Recipe not found")
                            }
                        }
                    }
                }
            }
        }
    }
}