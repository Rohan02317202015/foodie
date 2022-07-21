package com.rohan.foodie.hilt

import com.rohan.foodie.common.Constants
import com.rohan.foodie.data.remote.MealSearchAPI
import com.rohan.foodie.data.repository.MealDetailsRepositoryImpl
import com.rohan.foodie.data.repository.MealSearchRepistoryImpl
import com.rohan.foodie.data.repository.MealsRepositoryImpl
import com.rohan.foodie.domain.repository.MealDetailsRepository
import com.rohan.foodie.domain.repository.MealSearchRepository
import com.rohan.foodie.domain.repository.MealsRepository
import com.rohan.foodie.mvvm.models.MVVMRepository
import com.rohan.foodie.mvvm.models.MVVMRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HIltModules {


    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return MealSearchRepistoryImpl(mealSearchAPI)
    }


    @Provides
    fun provideMealDetails(searchMealSearchAPI: MealSearchAPI): MealsRepository {
        return MealsRepositoryImpl(searchMealSearchAPI)
    }

    @Provides
    fun provideMealRepository(searchMealSearchAPI: MealSearchAPI) : MVVMRepository{
        return MVVMRepositoryImpl(searchMealSearchAPI)
    }


}