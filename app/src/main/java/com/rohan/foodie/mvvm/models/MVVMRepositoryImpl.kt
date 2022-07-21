package com.rohan.foodie.mvvm.models

import com.rohan.foodie.common.Resource
import com.rohan.foodie.data.model.MealsDTO
import com.rohan.foodie.data.model.toDomainMeal
import com.rohan.foodie.data.model.toDomainMealDetails
import com.rohan.foodie.data.remote.MealSearchAPI
import com.rohan.foodie.domain.model.Meal
import com.rohan.foodie.domain.model.MealDetails
import retrofit2.HttpException
import java.io.IOException

class MVVMRepositoryImpl (private val mealSearchAPI: MealSearchAPI) : MVVMRepository {
    override suspend fun getMealSearch(s: String): Resource<List<Meal>> {
       return try {
            val data = mealSearchAPI.getSearchMealList(s)
            val domainData =
                if (data.meals != null) data.meals.map { it -> it.toDomainMeal() } else emptyList()
             Resource.Success(data = domainData)
        } catch (e: HttpException) {
            Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.localizedMessage ?: "Check Connectivity")
        } catch (e: Exception) {
           Resource.Error(message = e.localizedMessage ?: "Check Connectivity")        }
    }

    override suspend fun getMealDetails(id: String): Resource<List<MealDetails>> {
        return try{
            val data = mealSearchAPI.getMealDetails(id)
            val domainData = if (!data.meals.isNullOrEmpty()) data.meals.map { it -> it.toDomainMealDetails() } else emptyList()
             Resource.Success(data = domainData)
        } catch (e: HttpException) {
            Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.localizedMessage ?: "Check Connectivity")
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "Check Connectivity")
        }
    }
}

