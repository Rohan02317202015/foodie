package com.rohan.foodie.data.repository

import com.rohan.foodie.data.model.MealsDTO
import com.rohan.foodie.data.remote.MealSearchAPI
import com.rohan.foodie.domain.repository.MealsRepository

class MealsRepositoryImpl (private val mealSearchAPI: MealSearchAPI) : MealsRepository {
    override suspend fun getMealSearch(s: String): MealsDTO {
        return mealSearchAPI.getSearchMealList(s)
    }

    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}