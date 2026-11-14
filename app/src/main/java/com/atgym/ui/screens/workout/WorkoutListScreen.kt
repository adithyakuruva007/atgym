package com.atgym.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atgym.data.database.entity.Exercise
import com.atgym.ui.components.SearchBar
import com.atgym.ui.theme.Black
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun WorkoutListScreen(
    onExerciseClick: (Exercise) -> Unit,
    viewModel: WorkoutViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedGroup by viewModel.selectedMuscleGroup.collectAsState()
    val exercises by viewModel.filteredExercises.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        // Title
        Text(
            text = "WORKOUTS",
            color = Bone,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
        )
        Text(
            text = "Browse 100+ exercises across all muscle groups",
            color = Mute,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Search Bar
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChanged(it) },
            placeholder = "Search squats, bench, deadlift..."
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Muscle Group Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            viewModel.muscleGroups.forEach { group ->
                val isSelected = (group == selectedGroup)
                val bgColor = if (isSelected) Red else Coal
                val textColor = if (isSelected) Color.White else Bone
                val borderColor = if (isSelected) Red else Line

                Box(
                    modifier = Modifier
                        .background(bgColor)
                        .border(1.dp, borderColor)
                        .clickable { viewModel.onMuscleGroupSelected(group) }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = group,
                        color = textColor,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Count indicator
        Text(
            text = "${exercises.size} EXERCISES FOUND",
            color = Mute,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Exercises List
        if (exercises.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No exercises found.\nTry a different search or muscle group.",
                    color = Mute,
                    fontSize = 15.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(exercises, key = { it.id }) { exercise ->
                    ExerciseCard(
                        exercise = exercise,
                        onClick = { onExerciseClick(exercise) }
                    )
                }
            }
        }
    }
}
