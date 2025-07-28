# Job Aggregator SA

This repository contains a minimal Android application written in Kotlin using Jetpack Compose. The application demonstrates how to aggregate job postings for Saudi Arabia with support for Arabic and English locales, dark mode and basic Google AdMob integration.

## Modules
- **app** – Android application module written in Kotlin/Jetpack Compose.

## Features
- Multilingual support (English/Arabic)
- Dark/light theme
- Placeholder job fetching with approval logic
- Banner advertisement using Google Mobile Ads SDK

## Building
This project uses the standard Gradle build system. Make sure the Android SDK is installed and run:

```bash
./gradlew assembleDebug
```

## Note
The job-fetching implementation is only a stub. Replace `fetchJobs()` inside `JobViewModel` with real logic to collect jobs from Saudi government and company sources and implement approval flow as required.
