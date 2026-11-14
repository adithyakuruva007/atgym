package com.atgym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.atgym.data.database.entity.Exercise
import com.atgym.ui.screens.cardio.CardioScreen
import com.atgym.ui.screens.home.HomeScreen
import com.atgym.ui.screens.workout.WorkoutDetailScreen
import com.atgym.ui.screens.workout.WorkoutListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }

        composable(BottomNavItem.Workouts.route) {
            WorkoutListScreen(
                onExerciseClick = { exercise ->
                    selectedExercise = exercise
                    navController.navigate("workout_detail")
                }
            )
        }

        composable(BottomNavItem.Cardio.route) {
            CardioScreen()
        }

        composable("workout_detail") {
            selectedExercise?.let { exercise ->
                WorkoutDetailScreen(
                    exercise = exercise,
                    onBack = { navController.popBackStack() }
                )
            } ?: run {
                navController.popBackStack()
            }
        }
    }
}
