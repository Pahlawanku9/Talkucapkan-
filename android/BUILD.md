# Talking Avatar Android build

## GitHub Actions
The repository workflow uses Gradle 8.7 installed by GitHub Actions, Java 17,
and builds both debug and release APKs.

- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

The release APK is currently **unsigned** because no private signing key is
stored in the project. Sign it with your own keystore before distribution.

## Backend
Set the Modal backend URL and API token in the app's Settings screen.
The Android app expects HTTPS and sends `Authorization: Bearer <token>`.

## AIDE
Open the `android/` directory as the Android project. The project uses Kotlin,
Jetpack Compose, Java 17, and compileSdk/targetSdk 35. If AIDE asks for a
Gradle distribution, use Gradle 8.7 or a compatible installed version.

## Important
`gradle/wrapper/gradle-wrapper.properties` is included as the canonical
Gradle version. The GitHub workflow intentionally uses the hosted Gradle
distribution, so the repository does not depend on a locally generated
wrapper JAR.
