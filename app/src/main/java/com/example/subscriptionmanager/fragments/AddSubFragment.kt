package com.example.subscriptionmanager.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.data.Subscription
import com.example.subscriptionmanager.databinding.FragmentAddSubBinding
import com.example.subscriptionmanager.other.Constans
import com.example.subscriptionmanager.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
import java.util.*

class AddSubFragment : Fragment() {

    private var _binding: FragmentAddSubBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    var pickedPhoto: Uri? = null
    var pickedBitMap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
            saveNewSubscription()
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

        for (item in Constans.COMPANIES) {
            val chip = Chip(requireContext(), null, com.google.android.material.R.style.Widget_Material3_Chip_Suggestion)
            chip.apply {
                id = ViewCompat.generateViewId()
                text = item.name
                //TODO set chip icon resource (in Constans.COMPANIES)
                setChipIconResource(item.image)
                setOnClickListener {
                    binding.tiName.setText(chip.text)
                    //TODO add image
                }
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun saveNewSubscription() {

        viewModel.addSubscription(Subscription(
            ViewCompat.generateViewId(),
            binding.tiName.text.toString(),
            binding.tiDate.text.toString(),
            binding.tiPrice.text.toString(),
            R.drawable.netflix_icon_161073
        // TODO replace drawable with selected photo
        // TODO OR if empty replace drawable with image of subscription's first letter
            )
        )
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    @SuppressLint("SetTextI18n")
    fun showCalendar() {

        val c = Calendar.getInstance(TimeZone.getTimeZone("ECT"))
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            binding.tiDate.setText("$dayOfMonth/$monthOfYear/$year")
        }, year, month, day)
        dpd.show()
    }

    private fun selectPhoto() {

        val intent = Intent(Intent(Intent.ACTION_PICK))
        intent.type = "image/*"
        startActivityForResult(intent, 100)
        //TODO select photo from gallery
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            binding.ivImage.setImageURI(data?.data)
        }
    }
}