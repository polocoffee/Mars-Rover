package com.banklannister.marsrover.nav

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.banklannister.marsrover.R
import com.banklannister.marsrover.nav.Destination.Home

object Destination {

    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
    const val Photo = "photo/{roverName}?sol={sol}"
    const val Saved = "saved"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/${roverName}")
    }
    val photo: (roverName: String, sol: String) -> Unit = { roverName, sol ->
        navController.navigate("photo/${roverName}?sol=${sol}")
    }
}

sealed class Screen(
    val route: String,
    @StringRes
    val resourceId: Int,
    @DrawableRes
    val drawableId: Int
) {
    @SuppressLint("ResourceType")
    data object Home : Screen("home", R.string.rover, R.drawable.ic_mars_rover)
    @SuppressLint("ResourceType")
    data object Saved : Screen("saved", R.string.saved, R.drawable.ic_save)
}