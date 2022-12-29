package com.example.subscriptionmanager.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.data.Subscription
import com.example.subscriptionmanager.databinding.FragmentAddSubBinding
import com.example.subscriptionmanager.viewmodels.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddSubFragment : Fragment() {

    private var _binding: FragmentAddSubBinding? = null
    private val binding get() = _binding!!
    private var date: String = ""

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddSubBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showDatePicker(){
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = Date(it)
            date = "${calendar.get(Calendar.DAY_OF_MONTH)} ${SimpleDateFormat("MMMM").format(calendar.get(Calendar.MONTH))} ${calendar.get(Calendar.YEAR)}"
        }

        datePicker.show(parentFragmentManager, "DP")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.datePickerBtn.setOnClickListener{
            showDatePicker()
        }


        binding.btnSave.setOnClickListener {
            viewModel.addSubscription(Subscription(
                1, //TODO set ID for subscriptions
                binding.tiName.text.toString(),
                date,
                binding.tiPrice.text.toString(),
                R.drawable.netflix_icon_161073
            ))

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.btnImagePicker.setOnClickListener {
            //TODO add image
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}