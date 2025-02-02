package com.example.unitconverter

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.abs

class ConverterTest {

    private val delta = 0.0001

    /* Temperature Tests */
    @Test
    fun testCelsiusToFahrenheit() {
        val celsius = 0.0
        val expectedFahrenheit = 32.0
        val actualFahrenheit = celsiusToFahrenheit(celsius)
        assertEquals(expectedFahrenheit, actualFahrenheit, delta)

        // Additional test
        val celsius2 = 100.0
        val expectedFahrenheit2 = 212.0
        val actualFahrenheit2 = celsiusToFahrenheit(celsius2)
        assertEquals(expectedFahrenheit2, actualFahrenheit2, delta)
    }

    @Test
    fun testFahrenheitToCelsius() {
        val fahrenheit = 32.0
        val expectedCelsius = 0.0
        val actualCelsius = fahrenheitToCelsius(fahrenheit)
        assertEquals(expectedCelsius, actualCelsius, delta)

        // Additional test
        val fahrenheit2 = 212.0
        val expectedCelsius2 = 100.0
        val actualCelsius2 = fahrenheitToCelsius(fahrenheit2)
        assertEquals(expectedCelsius2, actualCelsius2, delta)
    }

    /* Length Tests */
    @Test
    fun testMetersToFeet() {
        val meters = 1.0
        val expectedFeet = 3.28084
        val actualFeet = metersToFeet(meters)
        assertEquals(expectedFeet, actualFeet, delta)
    }

    @Test
    fun testFeetToMeters() {
        val feet = 3.28084
        val expectedMeters = 1.0
        val actualMeters = feetToMeters(feet)
        assertEquals(expectedMeters, actualMeters, delta)
    }

    /* Weight Tests */
    @Test
    fun testKilogramsToPounds() {
        val kg = 1.0
        val expectedPounds = 2.20462
        val actualPounds = kilogramsToPounds(kg)
        assertEquals(expectedPounds, actualPounds, delta)
    }

    @Test
    fun testPoundsToKilograms() {
        val pounds = 2.20462
        val expectedKg = 1.0
        val actualKg = poundsToKilograms(pounds)
        assertEquals(expectedKg, actualKg, delta)
    }
}
