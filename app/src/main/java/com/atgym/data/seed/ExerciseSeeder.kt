package com.atgym.data.seed

import com.atgym.data.database.entity.Exercise

object ExerciseSeeder {

    fun getExercises(): List<Exercise> = listOf(
        // ==================== CHEST ====================
        Exercise(
            name = "Barbell Bench Press",
            muscleGroup = "Chest",
            equipment = "Barbell",
            description = "Lie on a flat bench and press a barbell upward from chest level to full arm extension.",
            isCompound = true
        ),
        Exercise(
            name = "Incline Barbell Bench Press",
            muscleGroup = "Chest",
            equipment = "Barbell",
            description = "Press a barbell upward while lying on a bench set to a 30-45 degree incline to target the upper chest.",
            isCompound = true
        ),
        Exercise(
            name = "Decline Barbell Bench Press",
            muscleGroup = "Chest",
            equipment = "Barbell",
            description = "Press a barbell upward while lying on a decline bench to emphasize the lower chest.",
            isCompound = true
        ),
        Exercise(
            name = "Dumbbell Bench Press",
            muscleGroup = "Chest",
            equipment = "Dumbbell",
            description = "Lie on a flat bench and press two dumbbells upward from chest level with a full range of motion.",
            isCompound = true
        ),
        Exercise(
            name = "Incline Dumbbell Press",
            muscleGroup = "Chest",
            equipment = "Dumbbell",
            description = "Press dumbbells upward while lying on an incline bench to target the upper chest fibers.",
            isCompound = true
        ),
        Exercise(
            name = "Decline Dumbbell Press",
            muscleGroup = "Chest",
            equipment = "Dumbbell",
            description = "Press dumbbells upward while lying on a decline bench to emphasize the lower chest.",
            isCompound = true
        ),
        Exercise(
            name = "Dumbbell Flyes",
            muscleGroup = "Chest",
            equipment = "Dumbbell",
            description = "Lie flat and open your arms wide with a slight elbow bend, then squeeze the dumbbells back together over your chest.",
            isCompound = false
        ),
        Exercise(
            name = "Incline Dumbbell Flyes",
            muscleGroup = "Chest",
            equipment = "Dumbbell",
            description = "Perform dumbbell flyes on an incline bench to isolate and stretch the upper chest.",
            isCompound = false
        ),
        Exercise(
            name = "Cable Crossover",
            muscleGroup = "Chest",
            equipment = "Cable",
            description = "Stand between two cable pulleys and bring the handles together in front of your chest in a hugging motion.",
            isCompound = false
        ),
        Exercise(
            name = "Pec Deck Machine",
            muscleGroup = "Chest",
            equipment = "Machine",
            description = "Sit in the pec deck machine and squeeze the padded arms together in front of your chest.",
            isCompound = false
        ),
        Exercise(
            name = "Chest Dips",
            muscleGroup = "Chest",
            equipment = "Bodyweight",
            description = "Lower your body on parallel bars while leaning forward to emphasize the chest muscles.",
            isCompound = true
        ),
        Exercise(
            name = "Push-Ups",
            muscleGroup = "Chest",
            equipment = "Bodyweight",
            description = "Lower and push your body up from the floor using your arms while maintaining a rigid plank position.",
            isCompound = true
        ),
        Exercise(
            name = "Diamond Push-Ups",
            muscleGroup = "Chest",
            equipment = "Bodyweight",
            description = "Perform push-ups with your hands close together forming a diamond shape to target the inner chest and triceps.",
            isCompound = true
        ),
        Exercise(
            name = "Wide Push-Ups",
            muscleGroup = "Chest",
            equipment = "Bodyweight",
            description = "Perform push-ups with your hands placed wider than shoulder-width to increase chest activation.",
            isCompound = true
        ),
        Exercise(
            name = "Landmine Press",
            muscleGroup = "Chest",
            equipment = "Barbell",
            description = "Press one end of a barbell anchored at the floor upward and forward in a controlled arc.",
            isCompound = true
        ),
        Exercise(
            name = "Machine Chest Press",
            muscleGroup = "Chest",
            equipment = "Machine",
            description = "Sit in a chest press machine and push the handles forward to full arm extension.",
            isCompound = true
        ),

        // ==================== BACK ====================
        Exercise(
            name = "Deadlift",
            muscleGroup = "Back",
            equipment = "Barbell",
            description = "Lift a loaded barbell from the floor to hip level by driving through your legs and extending your hips.",
            isCompound = true
        ),
        Exercise(
            name = "Barbell Row",
            muscleGroup = "Back",
            equipment = "Barbell",
            description = "Bend at the hips and row a barbell toward your lower chest while keeping your back flat.",
            isCompound = true
        ),
        Exercise(
            name = "Pendlay Row",
            muscleGroup = "Back",
            equipment = "Barbell",
            description = "Row a barbell explosively from the floor to your torso while keeping your back parallel to the ground.",
            isCompound = true
        ),
        Exercise(
            name = "Dumbbell Row",
            muscleGroup = "Back",
            equipment = "Dumbbell",
            description = "Brace one hand on a bench and row a dumbbell toward your hip with the opposite arm.",
            isCompound = true
        ),
        Exercise(
            name = "T-Bar Row",
            muscleGroup = "Back",
            equipment = "Barbell",
            description = "Straddle a landmine bar and row the weighted end toward your chest for a thick back.",
            isCompound = true
        ),
        Exercise(
            name = "Seated Cable Row",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "Sit at a cable row station and pull the handle toward your torso while squeezing your shoulder blades together.",
            isCompound = true
        ),
        Exercise(
            name = "Lat Pulldown",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "Pull a wide bar down to your upper chest while seated at a lat pulldown machine.",
            isCompound = true
        ),
        Exercise(
            name = "Wide Grip Lat Pulldown",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "Perform a lat pulldown with an extra-wide grip to emphasize the outer lats.",
            isCompound = true
        ),
        Exercise(
            name = "Close Grip Lat Pulldown",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "Perform a lat pulldown with a narrow or V-bar attachment to target the inner back and biceps.",
            isCompound = true
        ),
        Exercise(
            name = "Pull-Ups",
            muscleGroup = "Back",
            equipment = "Bodyweight",
            description = "Hang from a bar with an overhand grip and pull your chin above the bar using your back and arms.",
            isCompound = true
        ),
        Exercise(
            name = "Chin-Ups",
            muscleGroup = "Back",
            equipment = "Bodyweight",
            description = "Hang from a bar with an underhand grip and pull your chin above the bar, heavily engaging the biceps.",
            isCompound = true
        ),
        Exercise(
            name = "Face Pulls",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "Pull a rope attachment toward your face with elbows high to target the rear delts and upper back.",
            isCompound = true
        ),
        Exercise(
            name = "Straight Arm Pulldown",
            muscleGroup = "Back",
            equipment = "Cable",
            description = "With arms straight, push a bar down from shoulder height to your thighs to isolate the lats.",
            isCompound = false
        ),
        Exercise(
            name = "Hyperextensions",
            muscleGroup = "Back",
            equipment = "Bodyweight",
            description = "Lock your legs in a hyperextension bench and raise your torso by extending the lower back.",
            isCompound = false
        ),
        Exercise(
            name = "Rack Pulls",
            muscleGroup = "Back",
            equipment = "Barbell",
            description = "Perform a partial deadlift from knee height on a power rack to focus on the upper back and lockout.",
            isCompound = true
        ),
        Exercise(
            name = "Machine Row",
            muscleGroup = "Back",
            equipment = "Machine",
            description = "Sit in a row machine and pull the handles toward your torso in a controlled rowing motion.",
            isCompound = true
        ),

        // ==================== SHOULDERS ====================
        Exercise(
            name = "Overhead Press",
            muscleGroup = "Shoulders",
            equipment = "Barbell",
            description = "Press a barbell from shoulder height to full lockout overhead while standing.",
            isCompound = true
        ),
        Exercise(
            name = "Dumbbell Shoulder Press",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Press two dumbbells overhead from shoulder height while seated or standing.",
            isCompound = true
        ),
        Exercise(
            name = "Arnold Press",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Press dumbbells overhead while rotating your palms from facing you to facing forward throughout the movement.",
            isCompound = true
        ),
        Exercise(
            name = "Lateral Raises",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Raise dumbbells out to your sides until your arms are parallel to the floor to target the medial delts.",
            isCompound = false
        ),
        Exercise(
            name = "Front Raises",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Raise dumbbells in front of you to shoulder height to isolate the anterior deltoids.",
            isCompound = false
        ),
        Exercise(
            name = "Rear Delt Flyes",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Bend forward and raise dumbbells out to the sides to target the rear deltoids.",
            isCompound = false
        ),
        Exercise(
            name = "Cable Lateral Raises",
            muscleGroup = "Shoulders",
            equipment = "Cable",
            description = "Raise a cable handle out to the side from a low pulley to maintain constant tension on the medial delt.",
            isCompound = false
        ),
        Exercise(
            name = "Upright Row",
            muscleGroup = "Shoulders",
            equipment = "Barbell",
            description = "Pull a barbell straight up along your body to chin height with elbows flaring out to the sides.",
            isCompound = true
        ),
        Exercise(
            name = "Shoulder Face Pulls",
            muscleGroup = "Shoulders",
            equipment = "Cable",
            description = "Pull a rope attachment toward your face with elbows high to strengthen the rear delts and rotator cuff.",
            isCompound = true
        ),
        Exercise(
            name = "Shrugs",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Hold dumbbells at your sides and elevate your shoulders toward your ears to target the trapezius.",
            isCompound = false
        ),
        Exercise(
            name = "Barbell Shrugs",
            muscleGroup = "Shoulders",
            equipment = "Barbell",
            description = "Hold a barbell in front of you and shrug your shoulders upward to isolate the upper traps.",
            isCompound = false
        ),
        Exercise(
            name = "Dumbbell Shrugs",
            muscleGroup = "Shoulders",
            equipment = "Dumbbell",
            description = "Hold a dumbbell in each hand and shrug your shoulders straight up to build the upper trapezius.",
            isCompound = false
        ),
        Exercise(
            name = "Machine Shoulder Press",
            muscleGroup = "Shoulders",
            equipment = "Machine",
            description = "Sit in a shoulder press machine and push the handles overhead in a guided pressing motion.",
            isCompound = true
        ),
        Exercise(
            name = "Reverse Pec Deck",
            muscleGroup = "Shoulders",
            equipment = "Machine",
            description = "Sit facing a pec deck machine and push the handles backward to isolate the rear deltoids.",
            isCompound = false
        ),

        // ==================== LEGS ====================
        Exercise(
            name = "Barbell Back Squat",
            muscleGroup = "Legs",
            equipment = "Barbell",
            description = "Place a barbell on your upper back, squat down until thighs are parallel, and drive back up.",
            isCompound = true
        ),
        Exercise(
            name = "Front Squat",
            muscleGroup = "Legs",
            equipment = "Barbell",
            description = "Hold a barbell across the front of your shoulders and squat down to target the quads and core.",
            isCompound = true
        ),
        Exercise(
            name = "Goblet Squat",
            muscleGroup = "Legs",
            equipment = "Dumbbell",
            description = "Hold a dumbbell or kettlebell at your chest and squat down with an upright torso.",
            isCompound = true
        ),
        Exercise(
            name = "Bulgarian Split Squat",
            muscleGroup = "Legs",
            equipment = "Dumbbell",
            description = "Place your rear foot on a bench and lunge downward on the front leg for a unilateral squat.",
            isCompound = true
        ),
        Exercise(
            name = "Leg Press",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Sit in a leg press machine and push the weighted sled away by extending your legs.",
            isCompound = true
        ),
        Exercise(
            name = "Hack Squat",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Stand on a hack squat machine platform and squat down along the angled sled to target the quads.",
            isCompound = true
        ),
        Exercise(
            name = "Walking Lunges",
            muscleGroup = "Legs",
            equipment = "Dumbbell",
            description = "Step forward into alternating lunges while holding dumbbells to work the quads, glutes, and hamstrings.",
            isCompound = true
        ),
        Exercise(
            name = "Reverse Lunges",
            muscleGroup = "Legs",
            equipment = "Dumbbell",
            description = "Step backward into a lunge position while holding dumbbells to emphasize the glutes and quads.",
            isCompound = true
        ),
        Exercise(
            name = "Romanian Deadlift",
            muscleGroup = "Legs",
            equipment = "Barbell",
            description = "Hinge at the hips with a slight knee bend and lower a barbell along your legs to stretch the hamstrings.",
            isCompound = true
        ),
        Exercise(
            name = "Leg Extensions",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Sit in a leg extension machine and extend your knees to straighten your legs, isolating the quadriceps.",
            isCompound = false
        ),
        Exercise(
            name = "Leg Curls",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Lie face down on a leg curl machine and curl your heels toward your glutes to isolate the hamstrings.",
            isCompound = false
        ),
        Exercise(
            name = "Seated Calf Raises",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Sit in a calf raise machine and push up onto your toes to target the soleus muscle.",
            isCompound = false
        ),
        Exercise(
            name = "Standing Calf Raises",
            muscleGroup = "Legs",
            equipment = "Machine",
            description = "Stand on a calf raise machine and rise up onto your toes to target the gastrocnemius muscle.",
            isCompound = false
        ),
        Exercise(
            name = "Hip Thrusts",
            muscleGroup = "Legs",
            equipment = "Barbell",
            description = "Sit with your upper back against a bench and thrust a barbell upward by driving through your hips.",
            isCompound = true
        ),
        Exercise(
            name = "Sumo Deadlift",
            muscleGroup = "Legs",
            equipment = "Barbell",
            description = "Deadlift with a wide stance and toes pointed out to emphasize the inner thighs and glutes.",
            isCompound = true
        ),
        Exercise(
            name = "Step-Ups",
            muscleGroup = "Legs",
            equipment = "Dumbbell",
            description = "Step onto an elevated platform one leg at a time while holding dumbbells to build unilateral leg strength.",
            isCompound = true
        ),
        Exercise(
            name = "Sissy Squats",
            muscleGroup = "Legs",
            equipment = "Bodyweight",
            description = "Lean back while bending your knees and rising on your toes to deeply isolate the quadriceps.",
            isCompound = false
        ),
        Exercise(
            name = "Glute Kickbacks",
            muscleGroup = "Legs",
            equipment = "Cable",
            description = "Attach a cable to your ankle and kick your leg backward to isolate the glute muscles.",
            isCompound = false
        ),

        // ==================== ARMS - BICEPS ====================
        Exercise(
            name = "Barbell Curl",
            muscleGroup = "Arms",
            equipment = "Barbell",
            description = "Stand and curl a barbell upward by bending your elbows to contract the biceps.",
            isCompound = false
        ),
        Exercise(
            name = "Dumbbell Curl",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Stand and curl dumbbells upward alternately or simultaneously to target each bicep individually.",
            isCompound = false
        ),
        Exercise(
            name = "Hammer Curl",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Curl dumbbells with a neutral grip (palms facing each other) to target the brachialis and forearms.",
            isCompound = false
        ),
        Exercise(
            name = "Preacher Curl",
            muscleGroup = "Arms",
            equipment = "EZ Bar",
            description = "Rest your upper arms on a preacher bench pad and curl the bar upward to isolate the biceps.",
            isCompound = false
        ),
        Exercise(
            name = "Concentration Curl",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Sit and brace your elbow against your inner thigh while curling a dumbbell for peak bicep contraction.",
            isCompound = false
        ),
        Exercise(
            name = "Incline Dumbbell Curl",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Lie back on an incline bench and curl dumbbells to stretch and target the long head of the biceps.",
            isCompound = false
        ),
        Exercise(
            name = "Cable Curl",
            muscleGroup = "Arms",
            equipment = "Cable",
            description = "Stand in front of a low cable pulley and curl the handle upward for constant tension on the biceps.",
            isCompound = false
        ),
        Exercise(
            name = "EZ Bar Curl",
            muscleGroup = "Arms",
            equipment = "EZ Bar",
            description = "Curl an EZ curl bar using its angled grips to reduce wrist strain while training the biceps.",
            isCompound = false
        ),
        Exercise(
            name = "Spider Curl",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Lie face down on an incline bench and curl dumbbells straight down to intensely isolate the biceps.",
            isCompound = false
        ),

        // ==================== ARMS - TRICEPS ====================
        Exercise(
            name = "Tricep Pushdown",
            muscleGroup = "Arms",
            equipment = "Cable",
            description = "Push a cable bar or rope attachment downward by extending your elbows to isolate the triceps.",
            isCompound = false
        ),
        Exercise(
            name = "Overhead Tricep Extension",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Hold a dumbbell overhead with both hands and lower it behind your head by bending your elbows.",
            isCompound = false
        ),
        Exercise(
            name = "Skull Crushers",
            muscleGroup = "Arms",
            equipment = "EZ Bar",
            description = "Lie on a bench and lower an EZ bar toward your forehead by bending your elbows, then extend back up.",
            isCompound = false
        ),
        Exercise(
            name = "Close Grip Bench Press",
            muscleGroup = "Arms",
            equipment = "Barbell",
            description = "Perform a bench press with a narrow grip to shift the emphasis from the chest to the triceps.",
            isCompound = true
        ),
        Exercise(
            name = "Tricep Dips",
            muscleGroup = "Arms",
            equipment = "Bodyweight",
            description = "Lower and raise your body on parallel bars or a bench while keeping your torso upright to target the triceps.",
            isCompound = true
        ),
        Exercise(
            name = "Tricep Diamond Push-Ups",
            muscleGroup = "Arms",
            equipment = "Bodyweight",
            description = "Perform push-ups with a diamond hand position focusing on triceps engagement over chest.",
            isCompound = true
        ),
        Exercise(
            name = "Cable Overhead Extension",
            muscleGroup = "Arms",
            equipment = "Cable",
            description = "Face away from a cable machine and extend a rope attachment overhead to target the long head of the triceps.",
            isCompound = false
        ),
        Exercise(
            name = "Tricep Kickbacks",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Bend forward and extend a dumbbell backward by straightening your elbow to squeeze the triceps.",
            isCompound = false
        ),
        Exercise(
            name = "JM Press",
            muscleGroup = "Arms",
            equipment = "Barbell",
            description = "A hybrid of skull crushers and close grip bench press, lowering the bar to your chin level then pressing up.",
            isCompound = true
        ),

        // ==================== ARMS - FOREARMS ====================
        Exercise(
            name = "Wrist Curls",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Rest your forearms on a bench and curl a dumbbell upward using only your wrists to target the forearm flexors.",
            isCompound = false
        ),
        Exercise(
            name = "Reverse Wrist Curls",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Rest your forearms on a bench with palms facing down and extend your wrists upward to target the forearm extensors.",
            isCompound = false
        ),
        Exercise(
            name = "Farmer's Walk",
            muscleGroup = "Arms",
            equipment = "Dumbbell",
            description = "Hold heavy dumbbells at your sides and walk a set distance to build grip strength and forearm endurance.",
            isCompound = true
        ),
        Exercise(
            name = "Dead Hang",
            muscleGroup = "Arms",
            equipment = "Bodyweight",
            description = "Hang from a pull-up bar with arms fully extended to build grip strength and decompress the spine.",
            isCompound = false
        ),

        // ==================== CORE ====================
        Exercise(
            name = "Plank",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Hold a push-up position on your forearms, keeping your body in a straight line to engage the entire core.",
            isCompound = false
        ),
        Exercise(
            name = "Side Plank",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Balance on one forearm with your body sideways to target the obliques and lateral core stability.",
            isCompound = false
        ),
        Exercise(
            name = "Ab Wheel Rollout",
            muscleGroup = "Core",
            equipment = "Other",
            description = "Kneel and roll an ab wheel forward, extending your body then pulling it back to intensely work the abs.",
            isCompound = true
        ),
        Exercise(
            name = "Hanging Leg Raise",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Hang from a bar and raise your legs to hip level or above to target the lower abs and hip flexors.",
            isCompound = false
        ),
        Exercise(
            name = "Cable Crunch",
            muscleGroup = "Core",
            equipment = "Cable",
            description = "Kneel in front of a high cable pulley and crunch downward against the resistance to load the abdominals.",
            isCompound = false
        ),
        Exercise(
            name = "Russian Twist",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Sit with your torso leaned back and rotate side to side to target the obliques and transverse abdominals.",
            isCompound = false
        ),
        Exercise(
            name = "Mountain Climbers",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Hold a push-up position and drive your knees toward your chest in rapid alternation for cardio and core work.",
            isCompound = true
        ),
        Exercise(
            name = "Bicycle Crunches",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Lie on your back and pedal your legs while twisting your torso to touch opposite elbow to knee.",
            isCompound = false
        ),
        Exercise(
            name = "Decline Sit-Ups",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Hook your feet on a decline bench and perform sit-ups against gravity for increased abdominal activation.",
            isCompound = false
        ),
        Exercise(
            name = "Dragon Flags",
            muscleGroup = "Core",
            equipment = "Bodyweight",
            description = "Lie on a bench, grip the edge behind your head, and raise your entire body as a rigid lever to build extreme core strength.",
            isCompound = true
        ),
        Exercise(
            name = "Pallof Press",
            muscleGroup = "Core",
            equipment = "Cable",
            description = "Stand sideways to a cable machine and press the handle straight out, resisting rotation to train anti-rotational core stability.",
            isCompound = false
        ),
        Exercise(
            name = "Woodchops",
            muscleGroup = "Core",
            equipment = "Cable",
            description = "Pull a cable handle diagonally across your body from high to low (or low to high) to work the obliques and rotational core.",
            isCompound = true
        ),

        // ==================== FULL BODY ====================
        Exercise(
            name = "Burpees",
            muscleGroup = "Full Body",
            equipment = "Bodyweight",
            description = "Drop into a push-up, explode back up, and jump, combining cardio and full-body strength in one movement.",
            isCompound = true
        ),
        Exercise(
            name = "Clean and Press",
            muscleGroup = "Full Body",
            equipment = "Barbell",
            description = "Clean a barbell from the floor to your shoulders, then press it overhead in one explosive sequence.",
            isCompound = true
        ),
        Exercise(
            name = "Thrusters",
            muscleGroup = "Full Body",
            equipment = "Barbell",
            description = "Combine a front squat with an overhead press in one fluid movement for total body conditioning.",
            isCompound = true
        ),
        Exercise(
            name = "Turkish Get-Up",
            muscleGroup = "Full Body",
            equipment = "Kettlebell",
            description = "Rise from lying on the floor to standing while holding a kettlebell overhead with one arm.",
            isCompound = true
        ),
        Exercise(
            name = "Man Makers",
            muscleGroup = "Full Body",
            equipment = "Dumbbell",
            description = "Perform a push-up, renegade row on each arm, jump to your feet, and press the dumbbells overhead.",
            isCompound = true
        ),
        Exercise(
            name = "Kettlebell Swing",
            muscleGroup = "Full Body",
            equipment = "Kettlebell",
            description = "Hinge at the hips and explosively swing a kettlebell to shoulder height using hip drive and glute power.",
            isCompound = true
        )
    )
}
