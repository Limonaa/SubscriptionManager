package com.example.subscriptionmanager.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.data.Subscription
import com.example.subscriptionmanager.databinding.FragmentAddSubBinding
import com.example.subscriptionmanager.other.Constants
import com.example.subscriptionmanager.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
import java.util.*

class AddSubFragment : Fragment() {

    private var _binding: FragmentAddSubBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private var pickedImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddSubBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChips()

        binding.tiDate.setOnClickListener{
            showCalendar()
        }

        binding.btnSave.setOnClickListener {
            checkRequirements()
        }

        binding.ivImage.setOnClickListener {
            selectPhoto()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupChips() {

        for (item in Constants.COMPANIES) {
            val chip = Chip(requireContext(), null, com.google.android.material.R.style.Widget_Material3_Chip_Suggestion)
            chip.apply {
                id = ViewCompat.generateViewId()
                text = item.name
                //TODO set chip icon resource (in Constants.COMPANIES)
                setChipIconResource(item.image)
                setOnClickListener {
                    binding.tiName.setText(chip.text)
                    binding.ivImage.apply {
                        setImageBitmap(chipIcon?.toBitmap())
                        setColorFilter(Color.argb(0, 0, 0, 0))
                        pickedImage = chipIcon.toString().toUri()
                    }
                }
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun checkRequirements() {

        if (binding.tiName.text.isNullOrEmpty() || binding.tiPrice.text.isNullOrEmpty() || binding.tiDate.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Wypełnij pola", Toast.LENGTH_SHORT).show()
        }
        else {
            saveNewSubscription()
        }
    }

    private fun saveNewSubscription() {

        if (pickedImage != null) {
            viewModel.addSubscription(Subscription(
                ViewCompat.generateViewId(),
                binding.tiName.text.toString(),
                binding.tiDate.text.toString(),
                binding.tiPrice.text.toString(),
                binding.ivImage.drawable.toBitmap(),
                ""
            ))
        } else {
            viewModel.addSubscription(Subscription(
                ViewCompat.generateViewId(),
                binding.tiName.text.toString(),
                binding.tiDate.text.toString(),
                binding.tiPrice.text.toString(),
                null, //TODO create colorful drawable and randomly replace image,
            ""
            ))
        }
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    @SuppressLint("SetTextI18n")
    fun showCalendar() {

        val c = Calendar.getInstance(TimeZone.getTimeZone("ECT"))
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            binding.tiDate.setText("$dayOfMonth/${monthOfYear+1}/$year")
        }, year, month, day)
        dpd.show()
    }

    private fun selectPhoto() {

        val intent = Intent(Intent(Intent.ACTION_PICK))
        intent.type = "image/*"
        startActivityForResult(intent, Constants.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //IMAGE FOR SUBSCRIPTION
        if (requestCode == Constants.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            pickedImage = data?.data
            binding.ivImage.setImageURI(pickedImage)
            binding.ivImage.setColorFilter(Color.argb(0, 0, 0, 0))
        }
    }
}