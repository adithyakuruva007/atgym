package com.atgym.ui.screens.home

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.ui.theme.Ash
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.GreenSuccess
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red
import com.atgym.ui.theme.RedDim

@Composable
fun ProgressDashboard(
    weightKg: Float,
    heightCm: Float,
    bmi: Float,
    bmiCategory: String,
    weeklyCompletion: Int,
    totalWorkoutDays: Int,
    onUpdateWeight: (Float) -> Unit,
    onUpdateHeight: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var showWeightDialog by remember { mutableStateOf(false) }
    var showHeightDialog by remember { mutableStateOf(false) }

    val bmiColor = when (bmiCategory) {
        "Normal" -> GreenSuccess
        "Underweight", "Overweight" -> Color(0xFFF39C12)
        else -> Red
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Coal)
            .border(1.dp, Line)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PROGRESS & STATS",
                color = Bone,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Tap cards to edit",
                color = Mute,
                fontSize = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 1: Weight & BMI
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Weight Card
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Ash)
                    .border(1.dp, Line)
                    .clickable { showWeightDialog = true }
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "WEIGHT", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Mute, modifier = Modifier.size(13.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = String.format("%.1f", weightKg),
                            color = Bone,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = " kg",
                            color = Mute,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
                        )
                    }
                }
            }

            // BMI Card
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Ash)
                    .border(1.dp, Line)
                    .clickable { showHeightDialog = true }
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "BMI (${heightCm.toInt()}cm)", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit height", tint = Mute, modifier = Modifier.size(13.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = String.format("%.1f", bmi),
                            color = bmiColor,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Box(
                            modifier = Modifier
                                .background(bmiColor.copy(alpha = 0.2f), RoundedCornerShape(2.dp))
                                .border(1.dp, bmiColor.copy(alpha = 0.4f), RoundedCornerShape(2.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = bmiCategory,
                                color = bmiColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 2: Weekly Completion & Workout Streak
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Weekly Completion Card
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Ash)
                    .border(1.dp, Line)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "WEEKLY SETS", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$weeklyCompletion done",
                            color = Bone,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                    CircularProgress(percentage = (weeklyCompletion.coerceAtMost(30) / 30f), size = 46.dp)
                }
            }

            // Streak Card
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Ash)
                    .border(1.dp, Line)
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "TOTAL DAYS", color = Mute, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Icon(imageVector = Icons.Default.LocalFireDepartment, contentDescription = "Streak", tint = Red, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$totalWorkoutDays",
                            color = Red,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = " days",
                            color = Mute,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
                        )
                    }
                }
            }
        }
    }

    if (showWeightDialog) {
        EditValueDialog(
            title = "Update Weight",
            initialValue = weightKg.toString(),
            unit = "kg",
            onDismiss = { showWeightDialog = false },
            onConfirm = {
                val value = it.toFloatOrNull()
                if (value != null && value > 0f) {
                    onUpdateWeight(value)
                }
                showWeightDialog = false
            }
        )
    }

    if (showHeightDialog) {
        EditValueDialog(
            title = "Update Height",
            initialValue = heightCm.toString(),
            unit = "cm",
            onDismiss = { showHeightDialog = false },
            onConfirm = {
                val value = it.toFloatOrNull()
                if (value != null && value > 0f) {
                    onUpdateHeight(value)
                }
                showHeightDialog = false
            }
        )
    }
}

@Composable
fun CircularProgress(
    percentage: Float,
    size: Dp = 50.dp,
    strokeWidth: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val stroke = strokeWidth.toPx()
            drawCircle(
                color = Line,
                radius = (size.toPx() - stroke) / 2,
                style = Stroke(width = stroke)
            )
            drawArc(
                color = Red,
                startAngle = -90f,
                sweepAngle = 360f * percentage.coerceIn(0f, 1f),
                useCenter = false,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${(percentage * 100).toInt()}%",
            color = Bone,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditValueDialog(
    title: String,
    initialValue: String,
    unit: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var textValue by remember { mutableStateOf(initialValue) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Coal,
        title = {
            Text(text = title, color = Bone, fontWeight = FontWeight.Bold)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    label = { Text("Value ($unit)", color = Mute) },
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
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(textValue) },
                colors = ButtonDefaults.buttonColors(containerColor = Red)
            ) {
                Text("Save", color = Color.White, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Mute)
            }
        }
    )
}
