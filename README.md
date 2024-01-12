# Running an Android Application with OpenWeatherMap API Key

To successfully run this application that utilizes the OpenWeatherMap API, follow the steps below:

## Step 1: Create the `local.properties` file

Before running the application, you need to create a file named `local.properties` in the root directory of your project. This file should contain your OpenWeatherMap API key. Follow these steps:

1. Open your development IDE (e.g., Android Studio).
2. Navigate to the root directory of your project.
3. Create a new file called `local.properties`.

Inside the `local.properties` file, add the following line and replace `<your API key here>` with your OpenWeatherMap API key:

```properties
OpenWeatherMapApiKey="<your API key here>"
```

Save the file.

## Step 2: Run the Application

With the `local.properties` file configured correctly, you can now run the application on your device or emulator. Follow these steps:

### Option A: Using the IDE
1. Open your project in the development IDE.
2. Connect your Android device or start an emulator.
3. Click on the run button (usually a "play" icon) in your IDE.

The application will compile and run on your device/emulator, using the OpenWeatherMap API key provided in the `local.properties` file.

### Option B: Using the Command Line

Alternatively, you can use the command line to build and run the application. Follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of your project.
3. Run the following command to build and install the application on a connected device or emulator:
```bash
4. ./gradlew installDebug
```

Replace ./gradlew with gradlew.bat if you are on a Windows system.

That's it! The application should now be running successfully with the OpenWeatherMap API.