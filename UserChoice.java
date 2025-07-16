
/**
* Author: Giorgos Fotiou
* Written: 10/11/2023
* Last updated: 18/11/2023
*
* Compilation: javac -cp C:\Users\georg\eclipse-workspace\zaza\src\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp C:\Users\georg\eclipse-workspace\zaza\src\stdlib.jar Sudoku <N> <game-file>
*
* This programm 
*gets input from the user and checks if it is in the right form, displays the sudoku puzzle in graphics,
*and using the input to play the sudoku while in the meantime is checking if the game is within the rules of the sudoku.
*/
import java.util.Scanner;

public class UserChoice {
	int row;
	int column;
	int value;
	String input;

	// Initialising values of constructor UserChoice.
	public UserChoice(Scanner scanner) {
		row = 0;
		column = 0;
		value = 0;
		SetChoice(scanner);
	}

	// checks if input is in the correct format and gets input for column, row and
	// value.
	private void SetChoice(Scanner scanner) {
		boolean check = false;
		String realinput = "\\d+,\\d+=\\d+";
		while (check == false) {
			System.out.println("Enter your command in the following format:");
			System.out.println("+ i,j=val: for entering val at position (i,j)");
			System.out.println("+ i,j=0 : for clearing cell (i,j)");
			System.out.println("Notice: i,j,val numbering is from [1.." + Sudoku.N + "]");
			input = scanner.nextLine();
			if (this.input.matches(realinput)) {
				String[] part = input.split("[=,]");
				this.row = Integer.parseInt(part[0]);
				this.column = Integer.parseInt(part[1]);
				this.value = Integer.parseInt(part[2]); 
				check = true;
			} else {
				System.out.println("Error: wrong format of command!");
				Board.Displayboard();
			}
		}
	}

	// returns column.
	public int getColumn() {
		return column;
	}

	// returns row.
	public int getRow() {
		return row;
	}

	// returns value.
	public int getValue() {
		return value;
	}
}
