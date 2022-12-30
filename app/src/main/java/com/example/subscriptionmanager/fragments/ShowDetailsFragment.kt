package com.example.subscriptionmanager.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.adapters.PersonAdapter
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.FragmentShowDetailsBinding
import com.example.subscriptionmanager.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var personAdapter: PersonAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)

        setRecyclerView()
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedSubscription.collect {
                binding.tvSubName.text = it?.name
                binding.tvSubPrice.text = "Cena: ${it?.price}PLN"
                binding.tvNextPayment.text = it?.paymentDate
                //TODO payment destination
            }
        }
        personAdapter.setOnItemClickListener {
            //TODO  Show dialog to edit persons information
            Toast.makeText(requireContext(), "Clicked on person!", Toast.LENGTH_SHORT).show()
        }

        binding.fabAddPerson.setOnClickListener {
            viewModel.addPerson(
                Person(
                "Adam",
                "Jutro",
                "10",
                )
            )
            //TODO  Add dialog to add person
            setRecyclerView()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() = binding.rvPeople.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.peopleList.collect {
                personAdapter = PersonAdapter(it)
                adapter = personAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}