package com.example.subscriptionmanager.other

import com.example.subscriptionmanager.R
import com.example.subscriptionmanager.data.Company

object Constants {

    val COMPANIES = listOf(
    Company("Netflix", R.drawable.netflix_logo),
    Company("Spotify", R.drawable.spotify_logo),
    Company("HBO MAX", R.drawable.hbo_logo),
    Company("Disney +", R.drawable.disney_logo),
    Company("Amazon Prime", R.drawable.prime_logo),
    Company("Tidal", R.drawable.tidal_logo),
    Company("YT Premium", R.drawable.yt_logo),
    Company("Xbox Game Pass", R.drawable.xbox_logo),
    Company("Legimi", R.drawable.legimi_logo),
    Company("Bookbeat", R.drawable.bookbeat_logo),
    )

    val MONTHS = listOf("Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień")
    val ICON_COLORS = listOf(R.color.redIcon, R.color.purpleIcon, R.color.blueIcon, R.color.tealIcon, R.color.limeIcon, R.color.orangeIcon, R.color.deepOrangeIcon, R.color.indigoIcon)

    const val IMAGE_REQUEST_CODE: Int = 100
}

