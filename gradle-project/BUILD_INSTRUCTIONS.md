# CLVPrinter - Build Instructions

Rebranding dari **RapPrint** ke **CLVPrinter** selesai. Ikuti instruksi di bawah untuk build APK.

## ✅ Apa Yang Sudah Siap

- ✓ Package name: `com.clvprinter.smartprint`
- ✓ App name: `CLVPrinter`
- ✓ Brand colors (Palet Clavis)
- ✓ Logo & assets
- ✓ Gradle configuration updated
- ✓ All source files synced

## 🔧 Prerequisites

### Opsi 1: Android Studio (Recommended)
1. Download Android Studio dari https://developer.android.com/studio
2. Install dengan default settings
3. Android Studio akan auto-download SDK & build tools

### Opsi 2: Command Line Setup
Jika ingin build dari command line tanpa Android Studio:

#### Windows PowerShell:
```powershell
# Install Java JDK 11 or higher
winget install Oracle.JDK.17

# Install Android SDK (via Android SDK Manager atau manual download)
# Set environment variables:
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:ANDROID_HOME = "C:\Users\$env:USERNAME\AppData\Local\Android\Sdk"

# Add to PATH
$env:Path += ";$env:JAVA_HOME\bin;$env:ANDROID_HOME\tools\bin"
```

## 🚀 Build APK

### Method 1: Android Studio GUI
```
1. Open Android Studio
2. File → Open Project
3. Select: C:\Dev\PrintService\gradle-project
4. Wait for Gradle sync to complete
5. Build → Build Bundle(s) / APK(s)
6. Select "Build APK"
7. Wait for build to complete
8. APK output: app/build/outputs/apk/debug/app-debug.apk
```

### Method 2: Command Line
```powershell
cd C:\Dev\PrintService\gradle-project

# Debug APK
.\gradlew.bat assembleDebug

# Release APK (unsigned)
.\gradlew.bat assembleRelease

# Full build with tests
.\gradlew.bat build

# Clean rebuild
.\gradlew.bat clean assembleDebug
```

### Method 3: Gradle Wrapper (Recommended)
Gradle wrapper akan auto-download correct gradle version:
```powershell
cd C:\Dev\PrintService\gradle-project
.\gradlew.bat assembleDebug
```

## 📦 Output APK Locations

- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release-unsigned.apk`

## 🐛 Troubleshooting

### Error: "Could not find gradle"
- Install Gradle: https://gradle.org/install/
- Or use Android Studio which includes gradle

### Error: "Could not find java"
- Install Java JDK: https://www.oracle.com/java/technologies/downloads/
- Set JAVA_HOME environment variable

### Error: "SDK not found"
- Install Android SDK via Android Studio
- Set ANDROID_HOME environment variable to SDK location

### Error: "Compilation failed"
- Run: `.\gradlew.bat clean build`
- Check Java version: `java -version` (should be 11+)

## 📱 Install on Device

```powershell
# Install debug APK to connected device/emulator
adb install app/build/outputs/apk/debug/app-debug.apk

# Or uninstall first then install
adb uninstall com.clvprinter.smartprint
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📋 Project Structure

```
gradle-project/
├── app/
│   ├── src/main/
│   │   ├── java/com/clvprinter/smartprint/  (45 Java files)
│   │   ├── res/                              (Drawables, layouts, strings, colors)
│   │   └── AndroidManifest.xml
│   ├── build.gradle                          (Updated for CLVPrinter)
│   └── proguard-rules.pro
├── build.gradle                              (Project-level configuration)
├── settings.gradle                           (rootProject.name = 'CLVPrinter')
├── gradle.properties                         (Gradle configuration)
└── gradlew / gradlew.bat                     (Gradle wrapper scripts)
```

## ✨ Build Configuration Details

- **compileSdkVersion**: 36
- **targetSdkVersion**: 36
- **minSdkVersion**: 26
- **Build Tools**: 36.0.0
- **Java Version**: 8 (compatible with API 26+)
- **Gradle Version**: 8.2.0

---

**Build Complete!** 🎉 Setelah APK dibuat, Anda bisa:
- ✓ Install di Android device/emulator
- ✓ Upload ke Play Store
- ✓ Share ke testers
