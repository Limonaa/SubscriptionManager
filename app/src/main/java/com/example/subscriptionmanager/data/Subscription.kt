package com.example.subscriptionmanager.data

import android.graphics.Bitmap

data class Subscription(
    var id: Int,
    var name: String,
    var paymentDate: String,
    var price: String,
    var image: Bitmap?,
    var backgroundColor: Int?,
    var textImage: String,
    var renewals: Int,
) : java.io.Serializable
