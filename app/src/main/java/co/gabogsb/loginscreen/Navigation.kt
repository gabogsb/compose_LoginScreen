package co.gabogsb.loginscreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.gabogsb.loginscreen.Screens.AuthScreen
import co.gabogsb.loginscreen.Screens.FoodScreen
import co.gabogsb.loginscreen.Screens.HomeScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPreferences.getBoolean("loggedIn", false)

    NavHost(navController = navController, startDestination = if (userLoggedIn) "home" else "auth") {
        composable("auth") {
            AuthScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("food") {
            FoodScreen(navController)
        }
    }

}