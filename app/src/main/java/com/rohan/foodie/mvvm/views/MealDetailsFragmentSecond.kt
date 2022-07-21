package com.rohan.foodie.mvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rohan.foodie.databinding.FragmentMealDetailsSecondBinding
import com.rohan.foodie.mvvm.viewModels.MealDetailsVM
import com.rohan.foodie.presentation.meal_details.MealDetailsFragmentArgs
import com.rohan.foodie.presentation.meal_details.MealDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragmentSecond() : Fragment() {

    private var _binding: FragmentMealDetailsSecondBinding? = null
    val binding: FragmentMealDetailsSecondBinding
        get() = _binding!!

    private val viewModel: MealDetailsVM by viewModels()

    private val args: MealDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsSecondBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.mealId?.let {
            viewModel.getMealDetails(it)
        }


        viewModel.mealDetails.observe(viewLifecycleOwner){
            if(it.isLoading){
                //show loader
            }
            if(it.error.isNotBlank()){
                Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
            }
            it?.data?.let { data->
                binding.mealDetails = data
            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }


    }

}