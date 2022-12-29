package com.example.subscriptionmanager.viewmodels

import androidx.lifecycle.ViewModel
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.data.Subscription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val _subscriptionList = MutableStateFlow<MutableList<Subscription>>(mutableListOf())
    val subscriptionList: StateFlow<MutableList<Subscription>> = _subscriptionList
    fun addSubscription(subscription: Subscription) {
        _subscriptionList.value.add(subscription)
    }

    private val _peopleList = MutableStateFlow<MutableList<Person>>(mutableListOf())
    val peopleList: StateFlow<MutableList<Person>> = _peopleList
    fun addPerson(person: Person) {
        _peopleList.value.add(person)
    }

    private val _selectedSubscription = MutableStateFlow<Subscription?>(null)
    val selectedSubscription: StateFlow<Subscription?> = _selectedSubscription
    fun setSelectedSubscription(subscription: Subscription) {
        _selectedSubscription.value = subscription
    }
}