package com.rohan.foodie.mvvm.models

import com.rohan.foodie.common.Resource
import com.rohan.foodie.domain.model.Meal
import com.rohan.foodie.domain.model.MealDetails

interface MVVMRepository {
    suspend fun getMealSearch(s:String): Resource<List<Meal>>

    suspend fun getMealDetails(id:String): Resource<List<MealDetails>>
}