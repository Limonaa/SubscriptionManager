package com.example.subscriptionmanager.data

import java.io.Serializable

data class Person(
    var name: String,
    var price: String,
    var lastPaid: String,
) : Serializable
