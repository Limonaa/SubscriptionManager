package com.example.subscriptionmanager.data

import java.io.Serializable

data class Subscription(
    var name: String,
    var paymentDate: String,
    var price: String,
    //add image
) : Serializable
