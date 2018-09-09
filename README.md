# Notes app with Android Architecture Component
---

## Getting Started

## How to use

### Prerequisites
1) Install Java
    - [Direct Link](https://www.java.com/en/download/help/mac_install.xml)
                        (or)
    - using homebrew - ```brew cask install java```

2) Install Android Studio
    - [Direct Link](https://developer.android.com/studio/index.html)
                        (or)
    - using homebrew - ```brew cask install android-studio```

3) Install Android SDK
    - Install it inside android studio ( Tools -> Android -> SDK Manager )
                        (or)
    - using homebrew - ```brew cask install android-sdk```

4) Install gradle
    - [Direct Link](https://gradle.org/install/)
                        (or)
    - ```brew install gradle```

5) Configuring path variable
    ```
    export ANDROID_HOME=$HOME/Library/Android/sdk
    export PATH=$ANDROID_HOME/tools:$PATH
    export PATH=$ANDROID_HOME/platform-tools:$PATH
    ```
    Add these three to ~/.zshrc file and run ```source ~/.zshrc```

### Clone the repository

## Build Types, Flavours, Dimensions

Build Types - Environment
1) Debug
2) Release

Flavours:
1) en

### Useful Aliases for Dev
Add these lines to your ~/.zshrc file
```
# gradle alias
alias test="./gradlew clean testDebugUnitTest"
alias build="./gradlew clean assembleDebug"

```
### Android Requirements
* minSdkVersion = 19
* targetSdkVersion = 27
* compileSdkVersion = 27

# Author
Vrushali Raut.