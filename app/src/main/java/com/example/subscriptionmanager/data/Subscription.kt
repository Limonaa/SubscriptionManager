package com.example.subscriptionmanager.data

data class Subscription(
    var id: Int,
    var name: String,
    var paymentDate: String,
    var price: String,
    var image: Int?,
) : java.io.Serializable
