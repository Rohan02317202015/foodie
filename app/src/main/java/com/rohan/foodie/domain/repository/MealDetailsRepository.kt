package com.rohan.foodie.domain.repository

import com.rohan.foodie.data.model.MealsDTO

interface MealDetailsRepository {

    suspend fun getMealDetails(id:String):MealsDTO

}