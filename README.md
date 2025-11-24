
# MoodLog: Monitoring and Assessing Emotional Well-being

MoodLog is a console-based Java application designed to help users monitor and assess their emotional well-being. The program allows users to log their daily moods, identify triggers, complete psychology assessments, and track stress levels over time. Users can view their mood history, analyze statistics, and maintain journal entries alongside their mood data. The application addresses the need for accessible mental health tracking by providing a structured way to document emotional patterns and stress indicators.



## OOP Concepts Applied

### Encapsulation  
•	Private Data Members: The MoodTracker class encapsulates its data (logs, sc, formatter) as private fields, providing controlled access through public methods.  
•	MoodEntry Encapsulation: The MoodEntry class uses private fields with public getter methods to protect data integrity while allowing read access.

### Inheritance  
•	Display Class Hierarchy: The abstract Display class serves as a parent class for BannerDisplay and IconDisplay, implementing common methods like setColor(), resetColor(), and clearScreen() while requiring child classes to implement the abstract show() method.  
•	Test Class Hierarchy: The abstract BaseTest class provides shared functionality for all psychology tests through the askQuestions() method, while concrete test classes (WorkTest, AcademicsTest, PeerTest, FamilyTest) inherit this behavior and provide specific question sets.

### Polymorphism  
•	Display Polymorphism: The Display reference can point to either BannerDisplay or IconDisplay objects, with the correct show() method being called at runtime based on the actual object type.  
•	Test Polymorphism: The PsychologyTest interface is implemented by BaseTest and its subclasses, allowing the MoodTracker to handle different test types uniformly through the conductTest() method.


### Abstraction
•	Display Abstraction: The abstract Display class hides implementation details of screen clearing and color formatting, requiring only that subclasses define how to display their content.  
•	PsychologyTest Interface: The interface abstracts the test-taking process, defining a contract that all psychology tests must fulfill without dictating implementation details.  
•	BaseTest Abstract Class: Provides a template for psychology assessments while hiding the complexity of input validation and scoring logic.

### Interface Implementation

•	PsychologyTest Interface: Defines the contract for psychology tests with the conductTest() method, implemented by BaseTest and inherited by all test subclasses.  
•	Comparable Interface: MoodEntry implements Comparable<MoodEntry> to enable automatic sorting of mood entries by date in descending order.

## Program Structure

### Main Class
•	MoodLogApp: Entry point that initializes the application, displays menus, handles user navigation, and manages the main program loop.

### Display Package
•	Display: Parent class providing color formatting and screen clearing utilities.  
•	BannerDisplay: Displays the main application banner with ASCII art title.  
•	IconDisplay: Displays the welcome screen with mood emoticon ASCII art.  
### Model Package
•	MoodEntry: Data model representing a single mood log entry with date/time, mood, trigger, psychology test results, journal entry, and stress level. Implements file serialization and deserialization.
### Controller Package
•	MoodTracker: Core controller managing mood logging, data persistence, history viewing, statistics calculation, and log deletion. Handles file I/O operations and user input validation.

### TestTypes Package
•	PsychologyTest: Defines the contract for psychology assessments.   
•	BaseTest: Provides common testing logic including question presentation, input validation, and scoring.  
•	WorkTest: Work-related stress assessment with 10 specific questions.  
•	AcademicsTest: Academic stress assessment with 10 specific questions.  
•	PeerTest: Peer relationship stress assessment with 10 specific questions.  
•	FamilyTest: Family-related stress assessment with 10 specific questions.

## How to Run the Program
### Prerequisites
•	Java Development Kit (JDK) 21 or higher  
•	Command-line terminal with ANSI color support (recommended)
### Execution Steps
•	Run the compiled program MoodLogApp.java
First Run  
•	The program will create a mood_logs.txt file in the current directory to store mood entries  
•	Press Enter at the welcome screen to access the main menu

## Sample Output
![image alt](<img width="1071" height="281" alt="Screenshot 2025-11-21 224545" src="https://github.com/user-attachments/assets/156f9f98-c61b-4bb0-b64f-ef5d872ba67d" />
)
### Welcome Page
### Main Menu
### Logging a Mood (Option 1)
### Viewing Mood History (Option 2)
### Viewing Statistics (Option 3)
### Deleting a Log

  
## Authors

| Name                       | Role              | Contribution                                                                                           |
|----------------------------|-------------------|--------------------------------------------------------------------------------------------------------|
| Manalo, Godwin Andrei D.  | Lead Programmer   | Developed key features, improved user-friendliness, tested the entire app, and ensured reliable error handling and input validation. |
| Magadia, Lovely Jeann A.  | Initial Developer | Developed the foundation of the application.                                                           |
| Montalbo, Bianca A.       | Initial Developer | Developed the foundation of the application.                                                           |
| Manuel, Dennise May P.    | System Designer   | Develops the structure and specifications of a system based on user requirements.                      |



## Acknowledgements

First and foremost, we would like to express our deepest gratitude to Almighty God for His unwavering guidance, wisdom, and strength throughout the development of this project. His blessings have been the foundation of our perseverance and success in completing this endeavor as a team.

We extend our sincere appreciation to Sir Jayson Abratique, our Object-Oriented Programming instructor, for his guidance, expertise, and support throughout this project. His insightful lectures, constructive feedback, and dedication to teaching have been instrumental in shaping our understanding of OOP principles and their practical applications.

We are also grateful to:  
•	Our fellow classmates for their collaboration, encouragement, and feedback during development  
•	The Java Development Team for providing the robust standard library that made this project possible  
•	Our families and friends for their unwavering support and understanding during the intensive development period  
•	Each member of our team for their dedication, hard work, and collaborative spirit that made this project successful  

This project would not have been possible without the collective support, teamwork, and inspiration from all those mentioned above.



## Future Enhancement
•	Graphical User Interface (GUI): Transition from console-based interface to a modern GUI  
•	Export mood data to CSV or PDF format for external analysis   
•	Graphical visualization of mood trends over time   
•	Integration with calendar applications for automated reminders   
•	Multi-user support with separate profiles and data isolation   
•	Machine learning-based mood prediction and personalized recommendations   
•	Mobile application version for on-the-go mood tracking   
•	More psychology assessment types (sleep quality, exercise habits, nutrition)   
•	Customizable stress level thresholds based on individual baselines

## References
•	Oracle Java Documentation: https://docs.oracle.com/javase/8/docs/api/   
•	Oracle Java I/O Tutorial - File Reading and Writing: https://docs.oracle.com/javase/tutorial/essential/io/   
•	Java BufferedReader and BufferedWriter Documentation: https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html   
•	Java FileReader and FileWriter Classes: https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html   
•	Text File Handling in Java: Horstmann, C. S. (2019). Core Java Volume I - Fundamentals (11th ed.). Prentice Hall.   
•	ANSI Escape Code Standards for terminal color formatting   
•	Psychology assessment frameworks for stress measurement scales
