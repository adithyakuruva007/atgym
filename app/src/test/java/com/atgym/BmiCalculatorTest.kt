package com.atgym

import com.atgym.util.BmiCalculator
import org.junit.Assert.assertEquals
import org.junit.Test

class BmiCalculatorTest {

    @Test
    fun testBmiCalculation() {
        val weight = 70f
        val height = 175f
        val bmi = BmiCalculator.calculate(weight, height)
        // 70 / (1.75 * 1.75) = 22.857
        assertEquals(22.86f, bmi, 0.05f)
        assertEquals("Normal", BmiCalculator.getCategory(bmi))
    }

    @Test
    fun testBmiCategories() {
        assertEquals("Underweight", BmiCalculator.getCategory(17.5f))
        assertEquals("Normal", BmiCalculator.getCategory(22.0f))
        assertEquals("Overweight", BmiCalculator.getCategory(27.0f))
        assertEquals("Obese", BmiCalculator.getCategory(32.0f))
    }
}
