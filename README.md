# HomeSecure - Smart Home Control Application

A modern, complete, and aesthetically pleasing Smart Home Control Android Application built in **Java** using Material Design 3 components, Fragment architecture, Intent data passing, and System Notifications.

---

## 👤 Developer Profile & Credentials

- **Developer Name**: Utsav Choudhary
- **USN**: `25MCAR0125`
- **GitHub Repository**: [https://github.com/UtsavChoudhary25/HomeSecure](https://github.com/UtsavChoudhary25/HomeSecure)

---

## 🚀 Experiment Overview & Technology Stack

### 1. Concept & Technology
- **Android Java Architecture**: Pure Java implementation using `AppCompatActivity` and `androidx.fragment.app.Fragment`.
- **Material Design 3 Dashboard UI**: Dark glassmorphic aesthetic with custom rounded cards (`MaterialCardView`), glowing active borders, custom SeekBars (`custom_seekbar_track`, `custom_seekbar_thumb`), and status badges.
- **Dynamic Fragment Navigation**: Seamless switching between `LightingFragment`, `AirConditioningFragment`, and `SecurityFragment` inside a central `FrameLayout` container in `MainActivity`.
- **Intent Extras Data Transfer**: Passing security mode strings (`SECURITY_MODE`) from `SecurityFragment` to `SecurityStatusActivity`.
- **Android Notification System**: Real-time notifications utilizing `NotificationChannel` (Android 8.0+) and runtime `POST_NOTIFICATIONS` permission handling (Android 13+).
- **Activity & Fragment Lifecycle Logging**: Complete tracking of lifecycle callbacks logged in Logcat using the tag `"LIFECYCLE"`.

### 2. Demonstration Scenario
The application acts as a centralized dashboard for a smart home system:
1. **User Profile & Connected Devices**: Displays owner name **Utsav Choudhary** (`25MCAR0125`), system status ("Secure"), and network devices ("8 Devices Connected").
2. **Smart Lighting Control**: Select rooms (Living Room, Bedroom, Kitchen), toggle light state ON/OFF, and adjust brightness via custom SeekBar.
3. **Climate Control**: Adjust room temperature from 16°C to 30°C with real-time feedback on cooling status (Cooling High, Cooling Optimal, Eco/Heating).
4. **Security System**: Select security mode (Home, Away, Night), trigger system activation, generate Android status notifications, and view the dedicated security status screen.

---

## 📁 Project Folder & File Structure

```
HomeSecure
├── app/
│   ├── src/main/
│   │   ├── java/com/example/homesecure/
│   │   │   ├── MainActivity.java
│   │   │   ├── SecurityStatusActivity.java
│   │   │   ├── LightingFragment.java
│   │   │   ├── AirConditioningFragment.java
│   │   │   └── SecurityFragment.java
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── ic_home.xml, ic_lightbulb.xml, ic_ac.xml, ic_shield.xml
│   │   │   │   ├── ic_devices.xml, ic_check_circle.xml, ic_power.xml, ic_temperature.xml
│   │   │   │   ├── ic_homesecure_app_icon.xml
│   │   │   │   ├── bg_card.xml, bg_card_active.xml, bg_card_end.xml, bg_status_badge.xml
│   │   │   │   └── custom_seekbar_track.xml, custom_seekbar_thumb.xml
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_security_status.xml
│   │   │   │   ├── fragment_lighting.xml
│   │   │   │   ├── fragment_air_conditioning.xml
│   │   │   │   └── fragment_security.xml
│   │   │   ├── mipmap-anydpi-v26/
│   │   │   │   ├── ic_launcher.xml
│   │   │   │   └── ic_launcher_round.xml
│   │   │   └── values/
│   │   │       ├── colors.xml
│   │   │       ├── strings.xml
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── README.md
```

---

## 🎨 App Icon & Aesthetics

- **App Icon**: Custom vector adaptive launcher icon (`ic_homesecure_app_icon`) featuring a midnight dark shield with indigo highlight and central home emblem.
- **Theme**: Premium dark dashboard with `#0F111A` background, card strokes, and vibrant status indicators.

---

## 📱 Test Cases Overview

### 🔹 Test Case 1: Dashboard with Student Credentials, Connected Devices & Lighting Control
- **Description**: Verifies student identification card showing **Utsav Choudhary (USN: 25MCAR0125)**, system status, connected devices card ("8 Devices Connected"), and interactive Lighting Control fragment with room selector, toggle switch, and brightness seekbar.

---

### 🔹 Test Case 2: Air Conditioning Climate Control
- **Description**: Demonstrates real-time AC temperature adjustment (16°C – 30°C range) via SeekBar, showing dynamic status updating between Cooling (Optimal), Cooling (High), and Eco/Heating mode.

---

### 🔹 Test Case 3: Security Mode Selection & System Controls
- **Description**: Displays security mode selection (Home / Away / Night) via RadioGroup, active mode badge indicator, and security activation button.

---

### 🔹 Test Case 4: Security Activation & Status Confirmation Activity
- **Description**: Confirms system activation by passing `SECURITY_MODE` via Intent extras, raising a system notification (`"Security activated: Away"`), and navigating to `SecurityStatusActivity`.

---

## 📋 Activity & Fragment Lifecycle Logging (`LIFECYCLE` Tag)

The following callbacks are logged in Logcat during app navigation:
- `MainActivity`: `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`
- `LightingFragment`: `onCreateView`, `onDestroyView`
- `AirConditioningFragment`: `onCreateView`, `onDestroyView`
- `SecurityFragment`: `onCreateView`, `onDestroyView`
- `SecurityStatusActivity`: `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`

```log
D/LIFECYCLE: MainActivity: onCreate
D/LIFECYCLE: MainActivity: onStart
D/LIFECYCLE: MainActivity: onResume
D/LIFECYCLE: LightingFragment: onCreateView
D/LIFECYCLE: AirConditioningFragment: onCreateView
D/LIFECYCLE: SecurityFragment: onCreateView
D/LIFECYCLE: SecurityStatusActivity: onCreate
D/LIFECYCLE: SecurityStatusActivity: onStart
D/LIFECYCLE: SecurityStatusActivity: onResume
```

---

## 📌 Summary
The **HomeSecure** application compiles and runs directly on physical and virtual Android devices, fulfilling all academic and lab requirements cleanly in Java.
