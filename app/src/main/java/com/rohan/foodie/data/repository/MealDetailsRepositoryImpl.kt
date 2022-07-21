package com.rohan.foodie.data.repository

import com.rohan.foodie.data.model.MealsDTO
import com.rohan.foodie.data.remote.MealSearchAPI
import com.rohan.foodie.domain.repository.MealDetailsRepository

class MealDetailsRepositoryImpl(private val mealSearchAPI: MealSearchAPI) : MealDetailsRepository {

    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}