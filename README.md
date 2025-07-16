# Sudoku Game

A Java-based interactive Sudoku puzzle game that supports 4x4 and 9x9 grids.

## Features

- Load Sudoku puzzles from text files
- Interactive gameplay with input validation
- Real-time rule checking (row, column, box constraints)
- Save game progress to file
- Text-based graphical display

## Compilation

```bash
javac Board.java UserChoice.java Sudoku.java
```

## Usage

```bash
java Sudoku <N> <game-file>
```

- `N`: Grid size (4 or 9)
- `game-file`: Input file containing the initial puzzle

## Input Format

- Enter moves as: `i,j=val` (e.g., `1,2=5`)
- Clear cells with: `i,j=0`
- Save and exit with: `0,0=0`
- Coordinates and values range from 1 to N

## File Format

Input files should contain N×N integers separated by spaces, where 0 represents empty cells.

## Author

Giorgos Fotiou (November 2023)