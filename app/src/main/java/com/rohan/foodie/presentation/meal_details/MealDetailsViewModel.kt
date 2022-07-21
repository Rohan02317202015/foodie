package com.rohan.foodie.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohan.foodie.common.Resource
import com.rohan.foodie.domain.use_case.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: GetMealDetailsUseCase) :
    ViewModel() {


    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails



    fun getMealDetails(id: String) {
        mealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.update { data -> data.copy(isLoading = true) }
                }
                is Resource.Error -> {
                    _mealDetails.update { data -> data.copy(error = it.message ?: "") }
                }
                is Resource.Success -> {
                    _mealDetails.update { data -> data.copy(data = it.data?.get(0)) }
                }
            }
        }.launchIn(viewModelScope)
    }


}