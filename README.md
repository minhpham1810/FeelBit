# FeelBit - Your Daily Mood Companion

## Team Information
Minh Pham ('27) - Developer  
Aura Chuck Hernandez ('27) - Scrum Master  
Jean Marie Ngabonziza ('26) - Developer  
Hasiba Hasbin Royesh ('27) - Product Owner

## Project Overview
FeelBit is a mood-tracking app designed to help users reflect on their emotions and gain insights into their mental well-being. With an intuitive interface, FeelBit allows users to:

### Features
1. **Track Daily Moods**  
   Select from a range of 1–5 expressive emojis to capture your current mood.

2. **Identify Triggers**  
   Choose up to 6 potential triggers (e.g., school, relationships, work) from a dropdown menu to explore factors influencing your emotions.

3. **Express Freely**  
   Write freely in a journaling space to elaborate on your feelings and experiences in your own words.

By combining structured inputs with open-ended journaling, FeelBit empowers users to recognize patterns, understand triggers, and take proactive steps toward improved mental wellness.

## Project Structure
The app is organized into key packages:
- **Login and User Management:** Includes `MongoDbConnect` and `UserAccount` classes for user authentication and credential storage.
- **Mood Tracking:** Contains controllers handling journaling, mood tracking, system utilities, and statistical analysis.
- **Resources:** Includes an `assets` folder storing all FXML files used in the project.

## Third-Party Libraries
- **JavaFX 23.0.1**
- **Gemini API (1.5 Flash)**
- **MongoDB 8.0**

## Installation and Running the Application
### Prerequisites
- Java 17+
- MongoDB installed and running
- Gradle installed

### Steps to Run
1. Clone the repository.
   ```sh
   git clone <repository-url>
   cd FeelBit
   ```
2. Build and run the application.
   ```sh
   ./gradlew run
   ```
3. The login screen will appear in Scene Builder.

### Usage
1. **New Users:** Click "Create Account", enter your details, and log in.
2. **Returning Users:** Enter your credentials and log in.
3. Navigate using the home page buttons:
    - **Log an Emotion:** Select an emoticon, trigger, and write an entry.
    - **View Past Entries:** Review journal entries with API-generated wellness tips.
    - **View Statistics:** Check a pie chart of your most common emotions.

## Citations
- **Gemini API:** [Documentation](https://ai.google.dev/gemini-api/docs)
- **MongoDB:** [Version History](https://www.mongodb.com/resources/products/mongodb-version-history)
- **Stack Overflow (FXML):** [Reference](https://www.mongodb.com/resources/products/mongodb-version-history)