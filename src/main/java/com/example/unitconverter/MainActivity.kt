package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import com.example.unitconverter.celsiusToFahrenheit
import com.example.unitconverter.fahrenheitToCelsius
import com.example.unitconverter.metersToFeet
import com.example.unitconverter.feetToMeters
import com.example.unitconverter.kilogramsToPounds
import com.example.unitconverter.poundsToKilograms



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ConverterScreen()
                }
            }
        }
    }
}

enum class ConversionCategory(val displayName: String) {
    Temperature("Temperature"),
    Length("Length"),
    Weight("Weight")
}

@Composable
fun ConverterScreen() {
    var selectedCategory by remember { mutableStateOf(ConversionCategory.Temperature) }
    var inputValue by remember { mutableStateOf("") }
    var conversionDirection by remember { mutableStateOf(true) } // true: first->second, false: second->first
    var result by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Category Dropdown
        Text(text = "Select Conversion Category", style = MaterialTheme.typography.titleLarge)
        DropdownMenuExample(selectedCategory = selectedCategory, onSelectedChange = { selectedCategory = it })

        // Toggle conversion direction
        Button(onClick = { conversionDirection = !conversionDirection }) {
            Text(text = when (selectedCategory) {
                ConversionCategory.Temperature -> if (conversionDirection) "Celsius → Fahrenheit" else "Fahrenheit → Celsius"
                ConversionCategory.Length -> if (conversionDirection) "Meters → Feet" else "Feet → Meters"
                ConversionCategory.Weight -> if (conversionDirection) "Kilograms → Pounds" else "Pounds → Kilograms"
            })
        }

        // Input field
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Convert Button
        Button(
            onClick = {
                focusManager.clearFocus()
                val input = inputValue.toDoubleOrNull()
                if (input != null) {
                    result = when (selectedCategory) {
                        ConversionCategory.Temperature -> {
                            if (conversionDirection)
                                celsiusToFahrenheit(input).toString()
                            else
                                fahrenheitToCelsius(input).toString()
                        }
                        ConversionCategory.Length -> {
                            if (conversionDirection)
                                metersToFeet(input).toString()
                            else
                                feetToMeters(input).toString()
                        }
                        ConversionCategory.Weight -> {
                            if (conversionDirection)
                                kilogramsToPounds(input).toString()
                            else
                                poundsToKilograms(input).toString()
                        }
                    }
                } else {
                    result = "Invalid input"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        // Display result
        Text(text = "Result: $result", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun DropdownMenuExample(
    selectedCategory: ConversionCategory,
    onSelectedChange: (ConversionCategory) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selectedCategory.displayName)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ConversionCategory.values().forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category.displayName) },
                    onClick = {
                        onSelectedChange(category)
                        expanded = false
                    }
                )
            }
        }
    }
}
