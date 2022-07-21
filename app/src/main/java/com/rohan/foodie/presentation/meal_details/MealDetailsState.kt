package com.rohan.foodie.presentation.meal_details

import com.rohan.foodie.domain.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
) {
}