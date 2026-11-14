package com.atgym.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Search workouts...",
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Line, RoundedCornerShape(8.dp))
            .background(Coal, RoundedCornerShape(8.dp)),
        placeholder = {
            Text(text = placeholder, color = Mute)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Mute)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Clear", tint = Mute)
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Coal,
            unfocusedContainerColor = Coal,
            disabledContainerColor = Coal,
            cursorColor = Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = Bone,
            unfocusedTextColor = Bone
        ),
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )
}
