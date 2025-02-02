package com.example.unitconverter

fun celsiusToFahrenheit(celsius: Double): Double {
    return celsius * 9 / 5 + 32
}

    fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    /* Length conversion functions */
    fun metersToFeet(meters: Double): Double {
        return meters * 3.28084
    }

    fun feetToMeters(feet: Double): Double {
        return feet / 3.28084
    }

    /* Weight conversion functions */
    fun kilogramsToPounds(kg: Double): Double {
        return kg * 2.20462
    }

    fun poundsToKilograms(pounds: Double): Double {
        return pounds / 2.20462
    }
