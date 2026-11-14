package com.atgym.ui.screens.cardio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.data.database.entity.CardioLog
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red
import com.atgym.ui.theme.RedDim

@Composable
fun CardioHistoryList(
    logs: List<CardioLog>,
    onDeleteLog: (CardioLog) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "CARDIO LOGS (${logs.size})",
            color = Bone,
            fontSize = 15.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (logs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Coal)
                    .border(1.dp, Line)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No cardio logged yet.\nTap 'Log Session' to add one.",
                    color = Mute,
                    fontSize = 13.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Coal)
                    .border(1.dp, Line)
            ) {
                logs.forEachIndexed { index, log ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = log.activityName,
                                    color = Bone,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Box(
                                    modifier = Modifier
                                        .background(RedDim, RoundedCornerShape(2.dp))
                                        .padding(horizontal = 5.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = log.category,
                                        color = Bone,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "${log.durationMinutes} min • ${log.caloriesBurned} kcal" +
                                        if (log.distanceKm > 0f) " • ${log.distanceKm} km" else "",
                                color = Mute,
                                fontSize = 13.sp
                            )

                            if (log.notes.isNotEmpty()) {
                                Text(
                                    text = log.notes,
                                    color = Mute.copy(alpha = 0.8f),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = log.date, color = Mute, fontSize = 11.sp)
                            IconButton(
                                onClick = { onDeleteLog(log) },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Mute,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    if (index < logs.lastIndex) {
                        HorizontalDivider(color = Line, thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}
