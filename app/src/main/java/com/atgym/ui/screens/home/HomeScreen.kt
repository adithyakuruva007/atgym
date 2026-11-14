package com.atgym.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atgym.ui.components.StatCard
import com.atgym.ui.theme.Black
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // HTML Header Match: border-left: 10px solid var(--red)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Red,
                        topLeft = Offset(0f, 0f),
                        size = Size(8.dp.toPx(), size.height)
                    )
                }
                .padding(start = 16.dp, top = 2.dp, bottom = 2.dp)
        ) {
            Column {
                Text(
                    text = "WEEKLY\nSPLIT",
                    color = Bone,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 44.sp,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "One muscle group per day, hit from every angle. This is not a push pull split.",
                    color = Mute,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Progress Dashboard (Weight, BMI, Streak, Weekly Sets)
        ProgressDashboard(
            weightKg = uiState.weightKg,
            heightCm = uiState.heightCm,
            bmi = uiState.bmi,
            bmiCategory = uiState.bmiCategory,
            weeklyCompletion = uiState.weeklyCompletionCount,
            totalWorkoutDays = uiState.totalWorkoutDays,
            onUpdateWeight = { viewModel.updateWeight(it) },
            onUpdateHeight = { viewModel.updateHeight(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // HTML Rules section: 3 grid columns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Coal)
                .border(1.dp, Line),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatCard(
                value = "8 to 12",
                label = "reps in every set",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                value = "Failure",
                label = "on the last set",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                value = "3 sets",
                label = "per exercise",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Day Tracker (Mon-Sun tab strip)
        DayTracker(
            selectedDayIndex = uiState.selectedDayIndex,
            todayIndex = uiState.todayIndex,
            onDaySelected = { viewModel.selectDay(it) }
        )

        // Workout Panel (The selected day's workout from the plan)
        WorkoutPanel(
            dayIndex = uiState.selectedDayIndex,
            completedSets = uiState.completedSetKeys,
            onToggleSet = { day, ex, set -> viewModel.toggleSet(day, ex, set) },
            onClearTicks = { day -> viewModel.clearDay(day) }
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
