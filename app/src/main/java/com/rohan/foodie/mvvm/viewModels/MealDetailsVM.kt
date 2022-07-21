package com.rohan.foodie.mvvm.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohan.foodie.common.Resource
import com.rohan.foodie.data.model.toDomainMealDetails
import com.rohan.foodie.domain.repository.MealsRepository
import com.rohan.foodie.domain.use_case.GetMealDetailsUseCase
import com.rohan.foodie.mvvm.models.MVVMRepository
import com.rohan.foodie.presentation.meal_details.MealDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealDetailsVM  @Inject constructor(private val repo: MVVMRepository) : ViewModel(){

    private val _mealDetails = MutableLiveData<MealDetailsState>(MealDetailsState())
    val mealDetails: LiveData<MealDetailsState> = _mealDetails

    fun getMealDetails(id: String) {
        viewModelScope.launch {
            _mealDetails.value = MealDetailsState(isLoading = true)
            when(val response = repo.getMealDetails(id)){
                is Resource.Success -> {
                 _mealDetails.value = MealDetailsState(isLoading = false, error = "", data = response.data?.get(0) )
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(isLoading = false, error = response.message ?: "", data = null)
                }
                else -> {}
            }
        }
    }

}