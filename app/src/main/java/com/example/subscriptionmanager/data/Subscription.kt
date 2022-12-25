package com.example.subscriptionmanager.data

data class Subscription(
    var id: Int,
    var name: String,
    var paymentDate: String,
    var price: String,
    //add image
) : java.io.Serializable
