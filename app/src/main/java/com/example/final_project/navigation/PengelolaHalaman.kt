package com.example.final_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.final_project.Halaman.HalamanHome
import com.example.final_project.Halaman.HomeDestination
import com.example.final_project.tampilan.AuthenticationScreen
import com.example.final_project.tampilan.LoginDestination
import com.example.final_project.ui.add.AddScreen
import com.example.final_project.ui.add.DestinasiEntry
import com.example.final_project.ui.detail.DetailDestination
import com.example.final_project.ui.detail.DetailScreen
import com.example.final_project.ui.edit.EditDestination
import com.example.final_project.ui.edit.EditScreen
import com.example.final_project.ui.home.DestinasiMenu
import com.example.final_project.ui.home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = Modifier
    ) {
        composable(
            HomeDestination.route
        ){
            HalamanHome (navController = navController)
        }
        composable(
            LoginDestination.route
        ) {
            AuthenticationScreen(navController = navController)
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = {
                navController.popBackStack()
            })
        }
        composable(
            DestinasiMenu.route
        ) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.laptopId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val laptopId = backStackEntry.arguments?.getString(DetailDestination.laptopId)
            laptopId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestination.route}/$laptopId")
                        println("laptopId: $laptopId")
                    }
                )
            }
        }
        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.laptopId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val laptopId = backStackEntry.arguments?.getString(EditDestination.laptopId)
            laptopId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}