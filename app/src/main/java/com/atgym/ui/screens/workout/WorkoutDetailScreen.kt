package com.atgym.ui.screens.workout

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atgym.data.database.entity.Exercise
import com.atgym.ui.theme.Ash
import com.atgym.ui.theme.Black
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red
import com.atgym.ui.theme.RedDim

@Composable
fun WorkoutDetailScreen(
    exercise: Exercise,
    onBack: () -> Unit,
    viewModel: WorkoutViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var setNumber by remember { mutableIntStateOf(1) }
    var repsText by remember { mutableStateOf("10") }
    var weightText by remember { mutableStateOf("60.0") }
    var isFailure by remember { mutableStateOf(false) }
    var setLoggedToast by remember { mutableStateOf(false) }

    val history by viewModel.getExerciseHistory(exercise.id).collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Back Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Bone
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "EXERCISE DETAILS",
                color = Mute,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = exercise.name,
            color = Bone,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tags
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .background(RedDim, RoundedCornerShape(2.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = exercise.muscleGroup.uppercase(),
                    color = Bone,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .border(1.dp, Line, RoundedCornerShape(2.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = exercise.equipment,
                    color = Mute,
                    fontSize = 11.sp
                )
            }

            if (exercise.isCompound) {
                Box(
                    modifier = Modifier
                        .border(1.dp, Red, RoundedCornerShape(2.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "COMPOUND",
                        color = Red,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (exercise.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = exercise.description,
                color = Bone.copy(alpha = 0.8f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Log Set Panel
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Coal)
                .border(1.dp, Line)
                .padding(18.dp)
        ) {
            Text(
                text = "LOG A SET",
                color = Bone,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Set Number Picker
            Text(text = "SET NUMBER", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                for (s in 1..6) {
                    val isSelected = (s == setNumber)
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(if (isSelected) Red else Ash)
                            .border(1.dp, if (isSelected) Red else Line)
                            .clickable { setNumber = s },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$s",
                            color = if (isSelected) Color.White else Bone,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reps & Weight Input Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Reps
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "REPS", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = repsText,
                        onValueChange = { repsText = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Red,
                            unfocusedBorderColor = Line,
                            focusedTextColor = Bone,
                            unfocusedTextColor = Bone,
                            cursorColor = Red
                        ),
                        singleLine = true
                    )
                }

                // Weight (kg)
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "WEIGHT (KG)", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = weightText,
                        onValueChange = { weightText = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Red,
                            unfocusedBorderColor = Line,
                            focusedTextColor = Bone,
                            unfocusedTextColor = Bone,
                            cursorColor = Red
                        ),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Failure set checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { isFailure = !isFailure }
            ) {
                Checkbox(
                    checked = isFailure,
                    onCheckedChange = { isFailure = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Red,
                        uncheckedColor = Line,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "Failure set (pushed to maximum effort)",
                    color = Bone,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Log Button
            Button(
                onClick = {
                    val reps = repsText.toIntOrNull() ?: 10
                    val weight = weightText.toFloatOrNull() ?: 0f
                    viewModel.logWorkoutSet(
                        exerciseId = exercise.id,
                        exerciseName = exercise.name,
                        setNumber = setNumber,
                        reps = reps,
                        weightKg = weight,
                        isFailure = isFailure
                    )
                    setNumber++
                    setLoggedToast = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text(
                    text = "LOG SET $setNumber",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // History Section
        Text(
            text = "PREVIOUS LOGS",
            color = Bone,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (history.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Coal)
                    .border(1.dp, Line)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No previous logs for this exercise yet.", color = Mute, fontSize = 13.sp)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Coal)
                    .border(1.dp, Line)
            ) {
                history.forEachIndexed { index, log ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Set ${log.setNumber}: ${log.reps} reps @ ${log.weightKg} kg",
                                color = if (log.isFailureSet) Red else Bone,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (log.isFailureSet) {
                                Text(text = "Failure set", color = Red, fontSize = 11.sp)
                            }
                        }
                        Text(text = log.date, color = Mute, fontSize = 12.sp)
                    }
                    if (index < history.lastIndex) {
                        HorizontalDivider(color = Line, thickness = 0.5.dp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
