package com.atgym.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.ui.theme.Ash
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun DayTracker(
    selectedDayIndex: Int,
    todayIndex: Int,
    onDaySelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val daysInfo = listOf(
        "Mon" to "Legs",
        "Tue" to "Shoulders",
        "Wed" to "Chest",
        "Thu" to "Back",
        "Fri" to "Buffer",
        "Sat" to "Arms",
        "Sun" to "Off"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Line)
            .background(Coal)
            .horizontalScroll(rememberScrollState())
    ) {
        daysInfo.forEachIndexed { index, (dayAbbr, muscle) ->
            val isSelected = index == selectedDayIndex
            val isToday = index == todayIndex

            val bgColor = if (isSelected) Red else Coal
            val titleColor = if (isSelected) Color.White else Bone
            val subColor = if (isSelected) Color(0xFFFFD9DB) else Mute

            Box(
                modifier = Modifier
                    .width(92.dp)
                    .height(68.dp)
                    .background(bgColor)
                    .border(0.5.dp, Line)
                    .drawBehind {
                        if (isToday) {
                            val triangleColor = if (isSelected) Color.White else Red
                            val path = Path().apply {
                                moveTo(size.width, 0f)
                                lineTo(size.width - 24f, 0f)
                                lineTo(size.width, 24f)
                                close()
                            }
                            drawPath(path, color = triangleColor)
                        }
                    }
                    .clickable { onDaySelected(index) }
                    .padding(horizontal = 10.dp, vertical = 12.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = dayAbbr,
                        color = titleColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = muscle,
                        color = subColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
