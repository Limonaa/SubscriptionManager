package com.example.subscriptionmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.databinding.FragmentSecondBinding
import com.google.android.material.datepicker.MaterialDatePicker

class AddSubFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showDatePicker(){
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(parentFragmentManager, "DP")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.datePickerBtn.setOnClickListener(){
            showDatePicker()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}