# Kwazam Chess

**Kwazam Chess** is a custom chess-variant strategy game built in Java.  
It introduces redesigned piece movements, simplified interactions, and a unique win condition, while retaining a familiar board-game feel.  
The project uses **MVC architecture** and the **Observer pattern** to provide clean separation between logic, interface, and user actions.

This project was developed as part of an academic assignment and has been fully sanitized for public release.

---

## Features

### ♟ Chess-like gameplay with new rules
Kwazam Chess keeps core elements of classic chess but introduces:
- Modified movement rules for each piece  
- Simpler capture mechanics  
- An alternative victory condition  
- A streamlined interaction flow (select → highlight moves → move/eat/deselect)

### Graphical User Interface (GUI)
- Fully interactive board  
- Click-based piece selection and movement  
- Highlighted tiles for valid moves  
- Dialog pop-ups for new game, saving, loading, and exiting  

### Save & Load System
Kwazam Chess supports:
- Saving the current board state
- Loading the most recently saved game
- Resetting the board to its initial configuration

### MVC + Observer Design Patterns
From the documentation (pages 7–11):  
- **Model** handles game rules, piece logic, and state  
- **View** renders the GUI and visual updates  
- **Controller** interprets user input  
- **Observer pattern** automatically updates UI when game state changes  

This ensures scalable, maintainable code and clean separation of responsibilities.

---

## 🚀 How to Run the Game

cd "Kwazam_Chess"
javac run.java
java run

To compile manually for all java files

1. Go to parent folder and in terminal (for Windows using Powershell)
2. write :
javac -d bin (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

To run
1. Go to parent folder
2. write :

java -cp bin core/run
