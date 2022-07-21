package com.rohan.foodie.domain.repository

import com.rohan.foodie.data.model.MealsDTO

interface MealSearchRepository {

    suspend fun getMealSearch(s:String): MealsDTO

}