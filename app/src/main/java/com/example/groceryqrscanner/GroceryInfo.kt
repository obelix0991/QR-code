package com.example.groceryqrscanner

data class GroceryInfo(
    val itemName: String,
    val price: Double,
    val description: String,
    val categoryId: String,
    val manufacturer: String,
    val ingredients: List<String>? = null,
    val nutritionalInfo: NutritionalInfo? = null
)

data class NutritionalInfo(
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val sugar: Double
)
