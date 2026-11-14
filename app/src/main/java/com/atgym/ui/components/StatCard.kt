package com.atgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Red
) {
    Column(
        modifier = modifier
            .background(Coal)
            .border(1.dp, Line)
            .padding(14.dp, 12.dp)
    ) {
        Text(
            text = value,
            color = valueColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            lineHeight = 32.sp
        )
        Text(
            text = label,
            color = Mute,
            fontSize = 13.sp,
            lineHeight = 16.sp
        )
    }
}
