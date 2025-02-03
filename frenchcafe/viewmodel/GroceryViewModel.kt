package com.example.frenchcafe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frenchcafe.data.GroceryItem
import com.example.frenchcafe.data.GroceryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    private val _toBuyItems = MutableStateFlow<List<GroceryItem>>(emptyList())
    val toBuyItems: StateFlow<List<GroceryItem>> = _toBuyItems

    private val _purchasedItems = MutableStateFlow<List<GroceryItem>>(emptyList())
    val purchasedItems: StateFlow<List<GroceryItem>> = _purchasedItems

    init {
        viewModelScope.launch {
            repository.allItems.collect { items ->
                _toBuyItems.value = items.filter { !it.purchased }
                _purchasedItems.value = items.filter { it.purchased }
            }
        }
    }

    fun addItem(name: String) {
        val newItem = GroceryItem(name = name, purchased = false)
        viewModelScope.launch {
            repository.insertItem(newItem)
        }
    }

    fun updateItem(item: GroceryItem) {
        val updatedItem = item.copy(purchased = !item.purchased)

        _toBuyItems.value = _toBuyItems.value.map {
            if (it.id == item.id) updatedItem else it
        }.filter { !it.purchased }

        _purchasedItems.value = _purchasedItems.value.map {
            if (it.id == item.id) updatedItem else it
        }.filter { it.purchased }

        viewModelScope.launch {
            repository.updateItem(updatedItem)
        }
    }

    fun removeItem(item: GroceryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}