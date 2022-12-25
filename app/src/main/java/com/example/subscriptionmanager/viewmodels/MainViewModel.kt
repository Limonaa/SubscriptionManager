package com.example.subscriptionmanager.viewmodels

import androidx.lifecycle.ViewModel
import com.example.subscriptionmanager.data.Subscription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val _subscriptionList = MutableStateFlow<MutableList<Subscription>>(mutableListOf())
    val subscriptionList: StateFlow<MutableList<Subscription>> = _subscriptionList
    fun getSubscriptionList() {

    }
    fun addSubscription(subscription: Subscription) {
        _subscriptionList.value.add(subscription)
    }
}