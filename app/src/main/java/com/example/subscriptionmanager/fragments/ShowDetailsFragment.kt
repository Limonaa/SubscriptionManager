package com.example.subscriptionmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.adapters.PersonAdapter
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.FragmentShowDetailsBinding

class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var peopleList = mutableListOf(
            Person("Andrzej Mak", "8PLN", "18 października 2022"),
            Person("Maria Grabowska", "8PLN", "2 października 2022"),
            Person("Marek Łuszkiewicz", "8PLN", "30 października 2022")
        )

        val adapter = PersonAdapter(peopleList)
        binding.rvPeople.adapter = adapter
        binding.rvPeople.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}