package com.example.frenchcafe.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FrenchCafeColorScheme = lightColorScheme(
    primary = Color(0xFF728A6B),        // Medium Dusty Sage Green (adjust as needed)
    onPrimary = Color.White,           // Or a very light off-white for subtle contrast
    primaryContainer = Color(0xFFC7D9C4), // Lighter version of primary
    secondary = Color(0xFFDCA7B3),      // Light Dusty Rose/Peach
    onSecondary = Color.White,         // Or a darker, complementary color
    background = Color(0xFFF8F4F0),      // Off-White/Cream
    surface = Color(0xFFF8F4F0),       // Matching surface
    onPrimaryContainer = Color.Black,   // Or a dark grey/brown
    onSecondaryContainer = Color.Black, // Or a dark grey/brown
    onBackground = Color.Black,         // Or a dark grey/brown
    onSurface = Color.Black            // Or a dark grey/brown
)

@Composable
fun FrenchCafeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FrenchCafeColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}