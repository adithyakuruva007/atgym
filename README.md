# AtGym 🏋️‍♂️

A modern Android fitness tracker built with **Kotlin**, **Jetpack Compose** (Material 3), and **SQLite** (via Android Room), designed with an aggressive dark red/black aesthetic inspired by brutalist gym typography.

![Android CI](https://github.com/adithyakuruva007/atgym/actions/workflows/android.yml/badge.svg)

---

## 📸 Key Features

### 1. 🏠 Home Screen (Center Tab)
- **Pixel-Matched HTML UI**: Deep dark background (`#070606`), coal containers (`#131010`), and crimson red accents (`#D6101A`).
- **Interactive Day Tracker**: 7-day strip (Mon–Sun) with today's corner indicator and active day selection.
- **Weekly Workout Split**:
  - **Monday**: Legs (Squats, Lunges, Extensions, Hamstrings)
  - **Tuesday**: Shoulders (Press, Front/Lateral/Rear Raises, Bar Dips)
  - **Wednesday**: Chest (Bench Press strength lift, Dumbbell Press, Butterfly, Cable Push, Pushups)
  - **Thursday**: Back & Arms (Lat Pulldowns, Rows, Back Raises, Bicep/Forearm Curls)
  - **Friday**: Buffer / Upper Body Mix (Options A & B)
  - **Saturday**: Pure Arms (Biceps, Triceps, Forearms)
  - **Sunday**: Rest & Recovery
- **Interactive Set Completion**: Toggleable set boxes. The final set of each exercise is outlined with a dashed failure box.
- **Progress & Health Dashboard**:
  - Real-time Weight tracking (editable with instant persistence)
  - Height & BMI calculation with dynamic color-coded categories (Underweight, Normal, Overweight, Obese)
  - Weekly set completion arc
  - Total workout days streak

### 2. 🏋️ Workouts Database & Logger (Left Tab)
- **100+ Built-in Exercises**: Comprehensive library covering Chest, Back, Shoulders, Legs, Arms, Core, and Full Body movements.
- **Real-Time Search & Filtering**: Fast search bar with muscle group chips.
- **Set Logger**:
  - Custom sets, reps, and weight (kg) input
  - Failure set toggle
  - Exercise history log showing all previous sets and dates

### 3. 🏃 Cardio Protocol (Right Tab)
- **Structured Science-Backed Cardio Protocols**:
  - **LISS (Low Intensity Steady State)**: Incline treadmill walk, brisk walking, swimming, light cycling.
  - **HIIT (High Intensity Interval Training)**: Sprint intervals, assault bike, battle ropes, jump rope circuits.
  - **Steady State**: StairMaster, rowing machine, 5K tempo runs, road cycling.
- **Weekly Cardio Metrics**: Tracks total cardio minutes and estimated calories burned.
- **Cardio Session Logger**: Log duration, distance, calories, intensity, and notes with date tracking.

---

## 🛠️ Architecture & Tech Stack

- **Language**: Kotlin 2.1.0
- **UI Framework**: Jetpack Compose (Compose BOM 2024.12.01) + Material 3
- **Local Persistence**: SQLite via Android Room 2.6.1 + KSP
- **Architecture**: MVVM (Model-View-ViewModel) + Coroutines & Kotlin Flow
- **Navigation**: Jetpack Navigation Compose
- **Theme**: Dark-only high-contrast theme matching HTML reference

---

## 🚀 Building & Running

### Prerequisites
- JDK 17
- Android SDK (API 35, minSdk 26)

### CLI Build
```bash
./gradlew assembleDebug
```
The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

### Run Unit Tests
```bash
./gradlew testDebugUnitTest
```

---

## 📄 License
MIT License.
