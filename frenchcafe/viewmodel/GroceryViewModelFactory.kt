package com.example.frenchcafe.viewmodel // Your viewmodel package

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frenchcafe.data.GroceryRepository

class GroceryViewModelFactory(private val repository: GroceryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroceryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}