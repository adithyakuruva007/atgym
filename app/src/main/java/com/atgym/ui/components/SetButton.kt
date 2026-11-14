package com.atgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red
import com.atgym.ui.theme.RedDim

@Composable
fun SetButton(
    setNumber: Int,
    isCompleted: Boolean,
    isFailureSet: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isCompleted) Red else RedDim
    val backgroundColor = if (isCompleted) Red else Color.Transparent
    val textColor = if (isCompleted) Color.White else Mute

    Box(
        modifier = modifier
            .size(42.dp)
            .background(backgroundColor)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val halfStroke = strokeWidth / 2
                val pathEffect = if (isFailureSet && !isCompleted) {
                    PathEffect.dashPathEffect(floatArrayOf(10f, 6f), 0f)
                } else null

                drawRect(
                    color = borderColor,
                    topLeft = Offset(halfStroke, halfStroke),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth),
                    style = Stroke(width = strokeWidth, pathEffect = pathEffect)
                )
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = setNumber.toString(),
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
