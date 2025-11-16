# JavaNoughtsAndCrosses
# Java Noughts & Crosses

A Java implementation of the classic Noughts and Crosses (Tic-Tac-Toe) game, built using **JavaFX**, developed for the **COMP1322** course. This project was designed to demonstrate object-oriented programming (OOP) principles, clean architecture, and GUI interaction.

---

## Table of Contents

- [About](#about)  
- [Features](#features)  
- [Design & OOP Principles](#design--oop-principles)  
- [Prerequisites](#prerequisites)  
- [Running the Project](#running-the-project)  
- [Usage](#usage)  
- [Project Structure](#project-structure)  
- [How to Play](#how-to-play)  
- [Future Enhancements](#future-enhancements)  
- [Known Issues](#known-issues)  
- [Credits](#credits)  

---

## About

This is a GUI-based Noughts & Crosses game where two players can take turns to place **X** or **O** on a 3×3 grid. The game uses JavaFX for UI, and the core logic is structured around object-oriented design, making it modular, extensible, and understandable.

---

## Features

- Intuitive graphical user interface using JavaFX  
- Game state management (win, draw, ongoing)  
- Turn-based play (two players)  
- Clean separation between UI and game logic  
- Demonstrates core OOP concepts: **encapsulation**, **inheritance**, **polymorphism**, and **modularity**


## Design & OOP Principles

This project emphasises understanding and applying:

### **Encapsulation**

Game logic (board, players, win checking) is separated from UI code.

### **Modularity**

Each class handles a single responsibility, making the code easy to maintain.

### **Polymorphism / Extensibility**

The architecture allows plugging in new player types (e.g., AI players).

### **MVC-influenced structure**

While not pure MVC, the structure separates:

* **Model** — game logic
* **Controller/UI** — user interface interactions

## Prerequisites

You will need:

* **JDK 11+**
* **JavaFX SDK** (if not bundled with your IDE)
* An IDE such as IntelliJ IDEA, Eclipse, or NetBeans
* Git (optional)

## Installation

```bash
git clone https://github.com/TomNguyennn/JavaNoughtsAndCrosses.git
cd JavaNoughtsAndCrosses
```

## Running the Project

1. Open the project in your IDE
2. Ensure JavaFX is configured in the project SDK/module path
3. Run `Main.java`

---

## Usage

1. Launch the application
2. Choose grid size
3. Player **X** goes first
4. Click a grid cell to place your symbol
5. The game automatically checks for wins and draws
6. Restart or close when finished (depending on implemented features)

---

## Project Structure

```
src/
├── application/    # JavaFX controllers, UI logic
├── model/          # Game logic classes (Player, Board, Cell)
├── util/           # Optional helper classes
└── Main.java       # Application entry point
```

---

## How to Play

* Two players take turns clicking squares on the 3×3 board
* First player to get 3 symbols in a line (row/column/diagonal) wins
* If all squares are filled without a winner → **draw**

---

## Future Enhancements

* AI opponent (minimax algorithm)
* Larger boards (4×4, 5×5…)
* Scoreboard / match history
* Restart button or menu options
* Visual animations or themes
* Multiplayer over a network

---

## Known Issues

(Add any that apply; examples:)

* Restart button may not be implemented
* Layout may not scale on all displays
* Game state isn't saved between sessions

---

## Credits

* **Developer:** Tom Nguyen
* **Course:** COMP1322
* **Framework:** JavaFX
---

## Contact

GitHub: **[TomNguyennn](https://github.com/TomNguyennn)**
