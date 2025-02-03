package com.example.frenchcafe.data

import kotlinx.coroutines.flow.Flow

class GroceryRepository(private val groceryDao: GroceryDao) {
    val allItems: Flow<List<GroceryItem>> = groceryDao.getAllItems()

    suspend fun insertItem(item: GroceryItem) {
        groceryDao.insertItem(item)
    }

    suspend fun updateItem(item: GroceryItem) {
        groceryDao.updateItem(item)
    }

    suspend fun deleteItem(item: GroceryItem) {
        groceryDao.deleteItem(item)
    }
}