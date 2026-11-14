package com.atgym.ui.screens.cardio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atgym.ui.components.StatCard
import com.atgym.ui.theme.Ash
import com.atgym.ui.theme.Black
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.GreenSuccess
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun CardioScreen(
    viewModel: CardioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    var showLogDialog by remember { mutableStateOf(false) }
    var selectedActivityToLog by remember { mutableStateOf<CardioActivity?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "CARDIO PROTOCOL",
            color = Bone,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
        )
        Text(
            text = "Structured cardiovascular plan for fat loss & conditioning",
            color = Mute,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Coal)
                .border(1.dp, Line),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatCard(
                value = "${uiState.weeklyMinutes}m",
                label = "this week's cardio",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                value = "${uiState.weeklyCalories}",
                label = "calories burned",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Protocol Rules
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Coal)
                .border(1.dp, Line)
                .padding(14.dp)
        ) {
            Text(
                text = "GUIDELINES",
                color = Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• LISS: 20-40 min post-weights or on rest days for fat burning.\n" +
                        "• HIIT: 1-2x per week, 15-20 min. Avoid on heavy leg days.\n" +
                        "• Steady State: 25-35 min for building cardiovascular base.",
                color = Bone,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Tab Selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Coal)
                .border(1.dp, Line)
        ) {
            viewModel.categories.forEach { category ->
                val isSelected = (category == uiState.selectedCategory)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (isSelected) Red else Coal)
                        .clickable { viewModel.selectCategory(category) }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Bone,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Activities List in Selected Category
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            uiState.activities.forEach { activity ->
                CardioActivityCard(
                    activity = activity,
                    onLogClick = {
                        selectedActivityToLog = activity
                        showLogDialog = true
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Custom Log Button
        Button(
            onClick = {
                selectedActivityToLog = null
                showLogDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red),
            shape = RoundedCornerShape(2.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(
                text = "LOG CARDIO SESSION",
                color = Color.White,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // History
        CardioHistoryList(
            logs = uiState.logs,
            onDeleteLog = { viewModel.deleteLog(it) }
        )

        Spacer(modifier = Modifier.height(80.dp))
    }

    if (showLogDialog) {
        val initialName = selectedActivityToLog?.name ?: "Treadmill Walk"
        val initialCat = selectedActivityToLog?.category ?: uiState.selectedCategory
        CardioLogDialog(
            initialActivityName = initialName,
            initialCategory = initialCat,
            onDismiss = { showLogDialog = false },
            onConfirm = { name, cat, duration, dist, cals, intensity, notes ->
                viewModel.logCardio(
                    activityName = name,
                    category = cat,
                    durationMinutes = duration,
                    distanceKm = dist,
                    calories = cals,
                    intensity = intensity,
                    notes = notes
                )
                showLogDialog = false
            }
        )
    }
}

@Composable
fun CardioActivityCard(
    activity: CardioActivity,
    onLogClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val intensityColor = when (activity.intensity) {
        "Low" -> GreenSuccess
        "Medium" -> Color(0xFFF39C12)
        else -> Red
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Coal)
            .border(1.dp, Line)
            .clickable(onClick = onLogClick)
            .padding(14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = activity.name,
                    color = Bone,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .background(intensityColor.copy(alpha = 0.15f), RoundedCornerShape(2.dp))
                        .border(1.dp, intensityColor, RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "${activity.intensity.uppercase()} INTENSITY",
                        color = intensityColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${activity.durationRecommendation} • ~${activity.estimatedCalories}",
                color = Mute,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = activity.benefit,
                color = Bone.copy(alpha = 0.8f),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tap to log this session →",
                color = Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
