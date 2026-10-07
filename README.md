# FaIlculator

# Project Description
FaIlculator is a JavaFX-based calculator that is intentionally user-unfriendly. Instead of static digit and operator keys, keys are randomly generated and "fall" from the top of the screen, requiring users to click them in order to build expressions. 
However, in order to keep the user-friendliness to a minimum, these tiles do not behave predictability. They can: 
- randomly disappear when mouse hovers over them
- "die", or not send value to expression
- speed up to avoid being clicked

Despite these bugs, the logic of the calculator is completely sound, with a scratch-built arithmetic algorithm that correctly implements order of operations, accepts negative values, and identifies arithmetic logic and syntax errors.

**Architecture Overview**
- **Main.java** — Launches the application and initializes JavaFX.
- **MyController.java** — Manages all UI interactions, falling tile logic, and the arithmetic algorithm.
- **Tile.java** — Defines the behavior of individual tiles, including speed, disappearance, and click events.
- **MyView.fxml** — Handles the layout and organization of the UI elements.
- **application.css** — Applies the Windows 95-inspired color palette and retro styling.

**Algorithm Overview**
- Custom-built **arithmetic parser** with proper MDAS order and support for leading negative values.
- **70% digits / 30% operators** spawn ratio with random behavior probabilities:
  - 10% chance of tile disappearing on hover
  - 7.5% chance of dead clicks
  - 2.5% chance of persistent tile after click
- Expression safety checks with try-catch error handling for syntax and arithmetic errors.
  
**Areas for improvement**
- lack of deployability--requires full java and javafx sdk installed to run
- limit of functionality--can only handle basic arithmetic expressions 
# How to Install and Run the Project
Setup & Usage
Prerequisites

- Java 17+
- JavaFX SDK (version 23 or compatible)

**Running Locally**

1. Clone the Repository </br>

git clone https://github.com/MaximusLang/FaIlculator.git

cd FaIlculator

2. Set Up JavaFX (if needed) </br>
Add the JavaFX SDK path to your run configuration:

--module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml

3. Run the App </br>
Run Main.java in your IDE or via terminal:

javac --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml application/Main.java
java --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml application.Main

**Demo Video:** https://youtu.be/cPdibvGPnaY?si=ak361W0_cDyjy1Qq
<img width="722" height="932" alt="Screenshot 2025-10-12 074744" src="https://github.com/user-attachments/assets/19ff3ad8-b3f8-4150-84f5-b83c9308a92c" />
