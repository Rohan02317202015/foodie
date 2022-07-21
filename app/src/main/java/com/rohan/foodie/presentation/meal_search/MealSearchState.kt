package com.rohan.foodie.presentation.meal_search

import com.rohan.foodie.domain.model.Meal

data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)