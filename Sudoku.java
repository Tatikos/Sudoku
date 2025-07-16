
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sudoku {
	public static int N;
	public static String filename;
	private static int column;
	private static int row;
	private static int value;

	// gets input from the constructor in UserChoice class.
	private static void getuserinput(UserChoice Choice) {
		column = Choice.getColumn();
		row = Choice.getRow();
		value = Choice.getValue();
	}

	// checks if the sudoku game is completed
	public static boolean checkfinish() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Board.tableu[i][j] != 0)
					count++;
			}
		}
		if (count == Math.pow(N, 2) && Board.checkValitidy() == true)
			return true;
		else
			return false;
	}

	// assigns input in the sudoku according to the user.
	public static void Play() {
		Scanner scanner = new Scanner(System.in);
		boolean check = false;
		String outfile = "out-" + filename;
		do {
			Board.Displayboard();
			UserChoice meow = new UserChoice(scanner);
			getuserinput(meow);
			// checks if column,row or value is outside of the permitted range.
			if (column > N && column < 0 || row > N && row < 0 || value > N && value < 0)
				System.out.println("Error: i,j or val are outside the allowed range [1.." + N + "]!");

			else if (column > 0 && row > 0 && value == 0) {
				// checks if the cell is already occupied
				if (Board.tableu[row - 1][column - 1] < 0) {
					System.out.println("Error: cell already occupied");
				} else {
					Board.tableu[row - 1][column - 1] = 0;
					System.out.println("Value Cleared!");
				}
			} else if (column > 0 && row > 0 && value > 0 && value <= N) {
				if (Board.tableu[row - 1][column - 1] == 0) {
					Board.tableu[row - 1][column - 1] = value;
					if (Board.checkbox() == true) {
						Board.tableu[row - 1][column - 1] = 0;
						System.out.println("Error: Illegal value insertion! Same box rule not met!");
					} else if (Board.checkrow() == true) {
						Board.tableu[row - 1][column - 1] = 0;
						System.out.println("Error: Illegal value insertion! Same row rule not met!");
					} else if (Board.checkcolumn() == true) {
						Board.tableu[row - 1][column - 1] = 0;
						System.out.println("Error: Illegal value insertion! Same column rule not met!");
					} else
						System.out.println("value inserted!");

				} else {
					System.out.println("Error: cell already occupied");
				}
			} else if (column == 0 && row == 0 && value == 0) {
				String folderPath = "C:\\Users\\georg\\eclipse-workspace\\Libtext\\";
				String fileName = outfile;
				String path = folderPath + "\\" + fileName;
				File file = new File(folderPath);
				// checks if file already exists.
				if (!file.exists()) {
					file.mkdirs();
				}
				File newfile = new File(path);
				// prints the sudoku in a new file.
				try (PrintWriter out = new PrintWriter(newfile)) {
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							out.print(Board.tableu[i][j] + " ");
						}
						out.println();
					}
					out.flush();
					System.out.println("Saving game to " + outfile + "\nBye!");
					System.exit(0);
				} catch (FileNotFoundException e) {
					System.err.println("Error creating PrintWriter for file " + outfile);
					e.printStackTrace();
				}
			}
			check = checkfinish();
		} while (check == false);
		// prints the board when the game is completed.
		Board.Displayboard();
		System.out.println("Game completed!!!");
		scanner.close();
	}

	public static void main(String[] args) {
		// checks that there are on two arguments.
		if (args.length != 2) {
			System.out.println("Please give the dimension N followed by a <game-file> as the only 2 arguments");
			System.exit(0);
		}
		// gets the width of sudoku.
		N = Integer.parseInt(args[0]);
		// checks if the width of the sudoku is right.
		if (!(N == 4 || N == 9)) {
			System.out.println("The allowed value for N is either 4 or 9!\r\n"+ "Please re run the program with a valid value for N");
			System.exit(0);
		}
		// gets the name of the file.
		filename = args[1];
		// reads a sudoku from a file.
		Board.readboard();
		if (Board.checkrow() == true) {
			System.out.println("Error: This is not a valid sudoku! Same row rule not met! ");
			System.exit(0);
		} else if (Board.checkcolumn() == true) {
			System.out.println("Error: This is not a valid sudoku! Same column rule not met!");
			System.exit(0);
		} else if (Board.checkbox() == true) {
			System.out.println("Error: This is not a valid sudoku! Same box rule not met!");
			System.exit(0);
		}
		// calls function play.
		Play();
	}
}
