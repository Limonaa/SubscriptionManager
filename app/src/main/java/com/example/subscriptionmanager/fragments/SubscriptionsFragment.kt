package com.example.subscriptionmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.adapters.SubscriptionAdapter
import com.example.subscriptionmanager.databinding.FragmentSubscriptionsBinding
import com.example.subscriptionmanager.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class SubscriptionsFragment : Fragment() {

    private var _binding: FragmentSubscriptionsBinding? = null
    private val binding get() = _binding!!
    lateinit var subscriptionsAdapter: SubscriptionAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        subscriptionsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("subscription", it)
            }
            findNavController().navigate(
                R.id.action_FirstFragment_to_showDetailsFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvSubscriptions.apply {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.subscriptionList.collect {
                subscriptionsAdapter = SubscriptionAdapter(it)
                adapter = subscriptionsAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}