package com.example.onboardingscreen.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.onboardingscreen.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

// Onboarding pages list :->
val pages = listOf(
    Page(
        title = "Title 1",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit and typesetting industry",
        image = R.drawable.img1
    ),
    Page(
        title = "Title 2",
        description = "DLorem ipsum dolor sit amet, consectetur adipiscing elit and typesetting industry",
        image = R.drawable.img2
    ),
    Page(
        title = "Title 3",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit and typesetting industry",
        image = R.drawable.img3
    )
)