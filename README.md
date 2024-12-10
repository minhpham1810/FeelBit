# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA
### Course Info
Instructor: Lily Romano
Semester: Fall '24'
## Team Information
Minh Pham ('27) is a Developer. Aura Chuck Hernandez ('27) is a Scrum Master. Jean Marie Ngabonziza ('26) = Developer
Hasiba Hasbin Royesh ('27) is a Product Owner.
## Project Information
*FeelBit - Your Daily Mood Companion*

FeelBit is a mood-tracking app designed to help users reflect on their emotions and gain insights into their mental
well-being. With an intuitive interface, FeelBit allows users to:

1. Track Daily Moods
Select from a range of 1–5 expressive emojis to capture your current mood.

2. Identify Triggers
Choose up to 6 potential triggers (e.g., school, relationships, work) from a dropdown menu to explore 
factors influencing your emotions.

3. Express Freely
Write freely in a journaling space to elaborate on your feelings and experiences in your own words.

This multi-faceted approach makes tracking your emotional health both fun and meaningful. By combining structured inputs
with open-ended journaling, FeelBit empowers users to recognize patterns, understand triggers, and take proactive steps
toward improved mental wellness.

The package structure of our project: 

Our app is organized by central login and mood tracking packages. The former stores the MongoDbConnect and UserAccount
classes, which handle te validation and storage of user credentials. The feelBitMoodTracking package contains pertinent
controllers that handle how our app ensures journaling, mood tracking, system utilites, and statistic module abilities.
In our resources folder, we included a sub folder called assets that contains all FXML files in the project.


Third party libraries used include:
 1. JavaFX 23.0.1: 
 2. Gemini API (1.5 Flash)
 3. MongoDB 8.0 

URL to Our Video Presentation: 

## How to run it
1. Begin by opening the application by pressing the run button on the build.gradle file. Scene Builder should open and 
take you to FeelBit’s login page.

2. For new users, we encourage pressing the create account button at the bottom of the login page, where you’ll be taken
to a page with near identical username and password fields. Press the create account button. Enter new user details 
and press the Login button.

3. For returning users, enter your username and password in the login page. Press the Login button.

4. Upon entering the home page, the user will be presented with three buttons, which represent the different ways a user 
can log and view their entries. The topmost button allows users to press emoticons, select from a drop-down of trigger
options, and write their specialized entry. The middle button will let the user select a journal entry of their choice 
and review the entry, along with the API generated wellness tips. The last button allows users to view a pie chart of 
their most commonly entered emotions. Press the button of your choice.

5. Upon completion of entering your emotion log (pressing the first button and selecting emoticon, trigger, writing an entry, 
and pressing submit), you’ll be taken to a page that congratulates you on your submission and summarizes your journal entry,
along with API generated wellness tips.

CITATIONS:
Gemini API: https://ai.google.dev/gemini-api/docs?gad_source=1&gclid=Cj0KCQiA88a5BhDPARIsAFj595iOYmguyoxQiM8C_ol5Vq8vN6kP8gcU66kQGRJJCnrqiv2hpYbLvGsaAlYhEALw_wcB
MongoDb: https://www.mongodb.com/resources/products/mongodb-version-history
StackOverflow (FXML): https://www.mongodb.com/resources/products/mongodb-version-history

