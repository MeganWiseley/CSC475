package com.example.frenchcafe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
data class GroceryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val purchased: Boolean
) {
    fun copy(name: String = this.name, purchased: Boolean = this.purchased): GroceryItem =
        GroceryItem(id = this.id, name = name, purchased = purchased)
}