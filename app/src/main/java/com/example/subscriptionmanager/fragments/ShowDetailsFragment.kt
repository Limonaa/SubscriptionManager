package com.example.subscriptionmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.adapters.PersonAdapter
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.FragmentShowDetailsBinding

class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var peopleAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        setupRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("person", it)
//            }
//            findNavController().navigate(
//
//            )
            Toast.makeText(requireContext(), "Clicked on person!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvPeople.apply {
        val peopleList = mutableListOf(
            Person("Andrzej Mak", "8PLN", "Ostatnio zapłacono: 18 października 2022"),
            Person("Maria Grabowska", "8PLN", "Ostatnio zapłacono: 2 października 2022"),
            Person("Marek Łuszkiewicz", "8PLN", "Ostatnio zapłacono: 30 października 2022")
        )
        peopleAdapter = PersonAdapter(peopleList)
        adapter = peopleAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}