package com.rohan.foodie.domain.repository

import com.rohan.foodie.data.model.MealsDTO

interface MealsRepository {

    suspend fun getMealSearch(s:String): MealsDTO

    suspend fun getMealDetails(id:String):MealsDTO

}