package com.atgym.ui.screens.cardio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.atgym.ui.theme.Ash
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun CardioLogDialog(
    initialActivityName: String,
    initialCategory: String,
    onDismiss: () -> Unit,
    onConfirm: (
        activityName: String,
        category: String,
        durationMinutes: Int,
        distanceKm: Float,
        calories: Int,
        intensity: String,
        notes: String
    ) -> Unit
) {
    var activityName by remember { mutableStateOf(initialActivityName) }
    var category by remember { mutableStateOf(initialCategory) }
    var durationText by remember { mutableStateOf("30") }
    var distanceText by remember { mutableStateOf("0.0") }
    var caloriesText by remember { mutableStateOf("250") }
    var intensity by remember { mutableStateOf("Medium") }
    var notes by remember { mutableStateOf("") }

    val intensities = listOf("Low", "Medium", "High")

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Coal,
        title = {
            Text(
                text = "LOG CARDIO SESSION",
                color = Bone,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Activity Name
                OutlinedTextField(
                    value = activityName,
                    onValueChange = { activityName = it },
                    label = { Text("Activity Name", color = Mute) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Red,
                        unfocusedBorderColor = Line,
                        focusedTextColor = Bone,
                        unfocusedTextColor = Bone,
                        cursorColor = Red
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Duration & Calories
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = durationText,
                        onValueChange = { durationText = it },
                        label = { Text("Duration (min)", color = Mute) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Red,
                            unfocusedBorderColor = Line,
                            focusedTextColor = Bone,
                            unfocusedTextColor = Bone,
                            cursorColor = Red
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = caloriesText,
                        onValueChange = { caloriesText = it },
                        label = { Text("Calories (kcal)", color = Mute) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Red,
                            unfocusedBorderColor = Line,
                            focusedTextColor = Bone,
                            unfocusedTextColor = Bone,
                            cursorColor = Red
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Distance
                OutlinedTextField(
                    value = distanceText,
                    onValueChange = { distanceText = it },
                    label = { Text("Distance (km, optional)", color = Mute) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Red,
                        unfocusedBorderColor = Line,
                        focusedTextColor = Bone,
                        unfocusedTextColor = Bone,
                        cursorColor = Red
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Intensity
                Text(text = "INTENSITY", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    intensities.forEach { lvl ->
                        val isSelected = (lvl == intensity)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(if (isSelected) Red else Ash)
                                .border(1.dp, if (isSelected) Red else Line)
                                .clickable { intensity = lvl }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = lvl,
                                color = if (isSelected) Color.White else Bone,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Notes
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes (optional)", color = Mute) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Red,
                        unfocusedBorderColor = Line,
                        focusedTextColor = Bone,
                        unfocusedTextColor = Bone,
                        cursorColor = Red
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val duration = durationText.toIntOrNull() ?: 30
                    val distance = distanceText.toFloatOrNull() ?: 0f
                    val calories = caloriesText.toIntOrNull() ?: 250
                    onConfirm(
                        activityName,
                        category,
                        duration,
                        distance,
                        calories,
                        intensity,
                        notes
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Red),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text("LOG SESSION", color = Color.White, fontWeight = FontWeight.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Mute)
            }
        }
    )
}
