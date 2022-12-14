package com.example.subscriptionmanager.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.adapters.SubscriptionAdapter
import com.example.subscriptionmanager.data.Subscription
import com.example.subscriptionmanager.databinding.FragmentSecondBinding
import com.example.subscriptionmanager.databinding.FragmentSubscriptionsBinding

class SubscriptionsFragment : Fragment() {

    private var _binding: FragmentSubscriptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var subscriptionList = mutableListOf(
            Subscription("Netflix", "May 11", "$17"),
            Subscription("Youtube Premium", "May 18", "$5"),
            Subscription("HBO GO", "May 30", "$12"),
            Subscription("Youtube Kids", "April 1", "$75"),
            Subscription("PH", "December 1", "$22"),
            Subscription("Spotify", "July 22", "$2"),
            Subscription("Tik Tok Premium", "July 7", "$10"),
            Subscription("Snapchat", "September 17", "$6")
        )
        val addSubFragment = AddSubFragment()
        val adapter = SubscriptionAdapter(subscriptionList)
        binding.rvSubscriptions.adapter = adapter
        binding.rvSubscriptions.layoutManager = LinearLayoutManager(requireContext())
        val addingFullDialog = Dialog(requireContext(), android.R.style.ThemeOverlay)
        val addingFullDialogBinding = FragmentSecondBinding.inflate(LayoutInflater.from(requireContext()))

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            addingFullDialog.show()
            addingFullDialog.setContentView(addingFullDialogBinding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}