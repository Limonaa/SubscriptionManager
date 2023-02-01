package com.example.subscriptionmanager.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.adapters.PersonAdapter
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.FragmentShowDetailsBinding
import com.example.subscriptionmanager.viewmodels.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.abs

class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var personAdapter: PersonAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)

        setRecyclerView()
        return binding.root

    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        countDaysBetween()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedSubscription.collect {

                binding.tvSubName.text = it?.name
                binding.tvSubPrice.text = "Cena: ${it?.price}PLN"
                binding.tvNextPayment.text = it?.paymentDate
                binding.imageViewIcon.setImageBitmap(it?.image)
                it?.backgroundColor?.let { color -> binding.vImage.setBackgroundColor(color) }
                if (it?.image == null) {
                    binding.tvImage.text = it?.name?.first().toString()
                }
                binding.tvSubSpent.text = "Wydano: ${it?.renewals?.times(it.price.toInt()).toString()}PLN"
                binding.tvSubDuration.text = "Odnownienie za: ${countDaysBetween(it?.paymentDate.toString())} dni"

            }
            //TODO rounded image
        }

        binding.fabAddPerson.setOnClickListener {
            showAddPersonDialog()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() {
        binding.rvPeople.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.peopleList.collect {
                    personAdapter = PersonAdapter(it)
                    adapter = personAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddPersonDialog() {

        val builder = MaterialAlertDialogBuilder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.add_person_dialog, null)
        val editTextName = dialogLayout.findViewById<TextInputEditText>(R.id.tiName).text
        val editTextPrice = dialogLayout.findViewById<TextInputEditText>(R.id.tiPrice).text

        with(builder) {
            setTitle("Dodaj osobę")
            setPositiveButton("Dodaj") {dialog, which ->
                viewModel.addPerson(
                    Person(
                        editTextName.toString(),
                        editTextPrice.toString()
                    )
                )
                personAdapter.notifyDataSetChanged()
            }
            setNegativeButton("Anuluj") {dialog, which ->

            }
            setView(dialogLayout)
            show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun countDaysBetween(end: String): String {

        val nowDate = LocalDate.now()
        val shortFormatNOW = nowDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val diff = abs((sdf.parse(shortFormatNOW)?.time ?: 0) - (sdf.parse(end)?.time ?: 0))

        return ((diff / 86400000) - 730517).toString()
    }
}