package com.rohan.foodie.data.repository

import com.rohan.foodie.data.model.MealsDTO
import com.rohan.foodie.data.remote.MealSearchAPI
import com.rohan.foodie.domain.repository.MealSearchRepository

class MealSearchRepistoryImpl(private val mealSearchAPI: MealSearchAPI) : MealSearchRepository {

    override suspend fun getMealSearch(s: String): MealsDTO {
        return mealSearchAPI.getSearchMealList(s)
    }
}