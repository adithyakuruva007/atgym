package com.atgym.util

object BmiCalculator {

    fun calculate(weightKg: Float, heightCm: Float): Float {
        if (heightCm <= 0f) return 0f
        val heightM = heightCm / 100f
        return weightKg / (heightM * heightM)
    }

    fun getCategory(bmi: Float): String {
        return when {
            bmi < 18.5f -> "Underweight"
            bmi < 25.0f -> "Normal"
            bmi < 30.0f -> "Overweight"
            else -> "Obese"
        }
    }

    fun getHealthColor(bmi: Float): Long {
        return when {
            bmi < 18.5f -> 0xFFF39C12
            bmi < 25.0f -> 0xFF2ECC71
            bmi < 30.0f -> 0xFFF39C12
            else -> 0xFFE74C3C
        }
    }
}
