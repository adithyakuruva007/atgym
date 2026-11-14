package com.atgym.ui.screens.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atgym.ui.components.SetButton
import com.atgym.ui.theme.Bone
import com.atgym.ui.theme.Coal
import com.atgym.ui.theme.Line
import com.atgym.ui.theme.Mute
import com.atgym.ui.theme.Red

data class ExercisePlan(
    val name: String,
    val sets: Int,
    val note: String = "",
    val isKey: Boolean = false
)

data class PlanGroup(
    val header: String,
    val exercises: List<ExercisePlan>
)

data class DayPlan(
    val day: String,
    val muscleGroup: String,
    val title: String,
    val subtitle: String,
    val exercises: List<ExercisePlan> = emptyList(),
    val groups: List<PlanGroup> = emptyList(),
    val isRest: Boolean = false
)

val WEEKLY_PLAN = listOf(
    DayPlan(
        day = "Monday",
        muscleGroup = "Legs",
        title = "Legs",
        subtitle = "Heavy compound work first, then isolate the quads and hamstrings.",
        exercises = listOf(
            ExercisePlan("Weighted squats", 4),
            ExercisePlan("Weighted lunges", 3, note = "3 sets on each leg"),
            ExercisePlan("Leg extensions", 3),
            ExercisePlan("Hamstring extensions", 3)
        )
    ),
    DayPlan(
        day = "Tuesday",
        muscleGroup = "Shoulders",
        title = "Shoulders",
        subtitle = "Front, side and rear delts, finished with bar dips.",
        exercises = listOf(
            ExercisePlan("Shoulder press", 3),
            ExercisePlan("Front raises", 3),
            ExercisePlan("Lateral raises", 3),
            ExercisePlan("Rear delts", 3),
            ExercisePlan("Bar dips", 3)
        )
    ),
    DayPlan(
        day = "Wednesday",
        muscleGroup = "Chest",
        title = "Chest",
        subtitle = "Bench press is the strength lift of the week. Give it full effort.",
        exercises = listOf(
            ExercisePlan("Bench press", 5, note = "4 to 5 sets. This one matters most for strength.", isKey = true),
            ExercisePlan("Incline dumbbell press", 3),
            ExercisePlan("Butterfly press", 3),
            ExercisePlan("Cable push", 3),
            ExercisePlan("Pushups", 3)
        )
    ),
    DayPlan(
        day = "Thursday",
        muscleGroup = "Back",
        title = "Back",
        subtitle = "Lats and rows first, then biceps and forearms to finish.",
        exercises = listOf(
            ExercisePlan("Lat pulldown", 3),
            ExercisePlan("Wide grip rowing", 3),
            ExercisePlan("Narrow grip lat pulldown", 3),
            ExercisePlan("Dumbbell back raises", 3),
            ExercisePlan("Bicep curls", 3, note = "After the back work"),
            ExercisePlan("Forearm curls", 3, note = "After the back work")
        )
    ),
    DayPlan(
        day = "Friday",
        muscleGroup = "Buffer / Upper",
        title = "Buffer day",
        subtitle = "Catch up on anything missed this week. If nothing was missed, run an upper body mix of Tuesday and Wednesday.",
        groups = listOf(
            PlanGroup(
                header = "Option A: missed work",
                exercises = listOf(ExercisePlan("Do the session you skipped", 0, note = "Same sets, same rep range"))
            ),
            PlanGroup(
                header = "Option B: upper body mix",
                exercises = listOf(
                    ExercisePlan("Shoulder press", 3),
                    ExercisePlan("Lateral raises", 3),
                    ExercisePlan("Bar dips", 3),
                    ExercisePlan("Bench press", 4),
                    ExercisePlan("Incline dumbbell press", 3),
                    ExercisePlan("Cable push", 3)
                )
            )
        )
    ),
    DayPlan(
        day = "Saturday",
        muscleGroup = "Arms",
        title = "Arms",
        subtitle = "Purely arms. Every arm exercise, biceps, triceps and forearms.",
        exercises = listOf(
            ExercisePlan("Bicep curls", 3),
            ExercisePlan("Triceps work", 3, note = "Pushdowns, dips, extensions"),
            ExercisePlan("Forearm curls", 3)
        )
    ),
    DayPlan(
        day = "Sunday",
        muscleGroup = "Off",
        title = "Rest",
        subtitle = "",
        isRest = true
    )
)

@Composable
fun WorkoutPanel(
    dayIndex: Int,
    completedSets: Set<String>,
    onToggleSet: (dayIndex: Int, exerciseName: String, setNumber: Int) -> Unit,
    onClearTicks: (dayIndex: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val plan = WEEKLY_PLAN.getOrElse(dayIndex) { WEEKLY_PLAN[0] }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Coal)
            .border(1.dp, Line)
            .padding(22.dp)
    ) {
        // Title
        Text(
            text = plan.title,
            color = Bone,
            fontSize = 54.sp,
            fontWeight = FontWeight.Black,
            lineHeight = 56.sp,
            letterSpacing = 0.5.sp
        )

        if (plan.subtitle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = plan.subtitle,
                color = Mute,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (plan.isRest) {
            Text(
                text = "Nothing scheduled. Recover and start Monday fresh.",
                color = Bone,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else if (plan.groups.isNotEmpty()) {
            plan.groups.forEach { group ->
                Text(
                    text = group.header,
                    color = Bone,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                group.exercises.forEach { exercise ->
                    ExerciseRow(
                        dayIndex = dayIndex,
                        exercise = exercise,
                        completedSets = completedSets,
                        onToggleSet = onToggleSet
                    )
                }
            }
        } else {
            plan.exercises.forEach { exercise ->
                ExerciseRow(
                    dayIndex = dayIndex,
                    exercise = exercise,
                    completedSets = completedSets,
                    onToggleSet = onToggleSet
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Foot
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tap a set when finished.\nDashed box is failure set.",
                color = Mute,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .border(1.dp, Line, RoundedCornerShape(2.dp))
                    .clickable { onClearTicks(dayIndex) }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Clear ticks",
                    color = Bone,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ExerciseRow(
    dayIndex: Int,
    exercise: ExercisePlan,
    completedSets: Set<String>,
    onToggleSet: (dayIndex: Int, exerciseName: String, setNumber: Int) -> Unit
) {
    Column {
        HorizontalDivider(color = Line, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.name,
                    color = if (exercise.isKey) Red else Bone,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (exercise.note.isNotEmpty()) {
                    Text(
                        text = exercise.note,
                        color = Mute,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            if (exercise.sets > 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (setNumber in 1..exercise.sets) {
                        val key = "$dayIndex-${exercise.name}-$setNumber"
                        val isDone = completedSets.contains(key)
                        val isFailure = (setNumber == exercise.sets)

                        SetButton(
                            setNumber = setNumber,
                            isCompleted = isDone,
                            isFailureSet = isFailure,
                            onClick = { onToggleSet(dayIndex, exercise.name, setNumber) }
                        )
                    }
                }
            }
        }
    }
}
