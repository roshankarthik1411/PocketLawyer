package com.runanywhere.startup_hackathon20.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.runanywhere.startup_hackathon20.screens.*

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home
    ) {

        composable(NavRoutes.Home) {
            HomeScreen(
                onFIR = { navController.navigate(NavRoutes.FirScreen) },
                onDocumentCheck = { navController.navigate(NavRoutes.DocumentChecker) },
                onRightsInfo = { navController.navigate(NavRoutes.RightsInfo) },
                onEvidenceManager = { navController.navigate(NavRoutes.EvidenceManager) }
            )
        }

        composable(NavRoutes.FirScreen) {
            FIRScreen(onBack = { navController.popBackStack() })
        }

        composable(NavRoutes.DocumentChecker) {
            DocumentCheckerScreen(onBack = { navController.popBackStack() })
        }

        composable(NavRoutes.RightsInfo) {
            KnowYourRightsScreen(onBack = { navController.popBackStack() })
        }

        composable(NavRoutes.EvidenceManager) {
            EvidenceManagerScreen(onBack = { navController.popBackStack() })
        }
    }
}
