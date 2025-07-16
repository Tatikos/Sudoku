
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
import java.util.Scanner;

public class Board {
	private static int N = Sudoku.N;
	private static String filename = Sudoku.filename;
	public static int[][] tableu;

	// checks if the same number appears twice in the same column in the sudoku.
	public static boolean checkcolumn() {
		int number = 0, counter = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				counter = 0;
				number = Math.abs(tableu[i][j]);
				if (number != 0) {
					for (int k = 0; k < N; k++) {
						if (Math.abs(tableu[k][j]) == number) {
							counter++;
						}
					}
				}
				if (counter > 1) {
					return true;
				}
			}
		}
		return false;
	}

	// checks if the same number appears twice in the same row in the sudoku.
	public static boolean checkrow() {
		int number = 0, counter = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				counter = 0;
				number = Math.abs(tableu[i][j]);
				if (number != 0) {
					for (int k = 0; k < N; k++) {
						if (Math.abs(tableu[i][k]) == number) {
							counter++;
						}
					}
				}
				if (counter > 1) {
					return true;
				}
			}
		}

		return false;
	}

	// checks if the same number appears twice in the same sqrt(N)*sqrt(N) box in
	// the sudoku.
	public static boolean checkbox() {
		int counter = 0, start = 0, temp = 0, end = 0;
		for (int r = 0; r < Math.sqrt(N); r++) {
			end = 0;
			for (int g = 0; g < Math.sqrt(N); g++) {
				for (int i = start; i < start + Math.sqrt(N) && i < N; i++) {
					for (int j = end; j < end + Math.sqrt(N) && j < N; j++) {
						counter = 0;
						temp = Math.abs(tableu[i][j]);
						if (temp != 0) {

							for (int a = start; a < start + Math.sqrt(N) && i < N; a++) {
								for (int b = end; b < end + Math.sqrt(N) && j < N; b++) {
									if (Math.abs(tableu[a][b]) == temp) {
										counter++;
									}
									if (counter > 1) {
										return true;
									}
								}
							}
						}
					}
				}
				end += Math.sqrt(N);
			}
			start += Math.sqrt(N);
		}
		return false;
	}

	// checks if the sudoku is a valid one.
	public static boolean checkValitidy() {
		int counter = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Math.abs(tableu[i][j]) >= 0 && Math.abs(tableu[i][j]) <= N) {
					counter++;
				}
			}
		}
		if (counter != Math.pow(N, 2))
			return false;
		else if (checkbox() == true)
			return false;
		else if (checkrow() == true)
			return false;
		else if (checkcolumn() == true)
			return false;
		else
			return true;
	}

	// reads the contents of the file input.
	public static void readboard() {
		tableu = new int[N][N];
		String path = "C:\\Users\\georg\\eclipse-workspace\\Libtext\\" + filename;
		File fin = new File(path);
		int i = 0, count = 0;
		try {
			Scanner Scanner = new Scanner(fin);
			while (Scanner.hasNextInt()) {
				Scanner.nextInt();
				count++;
			}
			Scanner.close();
			if (count > Math.pow(N, 2)) {
				System.out.println("Error: Illegal number in input file!");
				System.exit(0);
			} else if (count < Math.pow(N, 2)) {
				System.out.println("Error: Missing values from file!");
				System.exit(0);
			} else {
				Scanner = new Scanner(fin);
				while (Scanner.hasNextInt()) {
					for (i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							tableu[i][j] = Scanner.nextInt();
							if(Math.abs(tableu[i][j]) > N){
								System.out.println("Error: This is not a valid sudoku! Numbers inside sudoku are not valid!");
								System.exit(0);
							}
						}
					}
				}
			}

			Scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found " + path);
			e.printStackTrace();
		}
	}

	// displays the sudoku in text based graphics.
	public static void Displayboard() {
		int i, j, a = 0;
		if (N == 4) {
			for (int d = 0; d < Math.sqrt(N); d++) {
				for (i = 0; i < Math.sqrt(N); i++) {
					if (i == 0)
						System.out.print("+");
					for (j = 0; j < Math.sqrt(tableu[i].length); j++) {
						if (i == Math.sqrt(N) || i == 0)
							System.out.print("------+");

					}
					if (i == 0)
						System.out.println();

					for (j = 0; j < N; j++) {
						if (j == 0 || j == Math.sqrt(N))
							System.out.print("|");
						if (tableu[a][j] == 0)
							System.out.print(" . ");
						else if (tableu[a][j] < 0)
							System.out.print("(" + Math.abs(tableu[a][j]) + ")");
						else
							System.out.printf("%2d ", tableu[a][j]);

					}
					System.out.println("|");
					a++;
				}
			}
			System.out.print("+");
			for (j = 0; j < Math.sqrt(N); j++) {
				System.out.print("------+");
			}
			System.out.println();
		}

		else {
			a = 0;
			for (int k = 0; k < Math.sqrt(N); k++)
				for (i = 0; i < Math.sqrt(N); i++) {
					if (i == 0)
						System.out.print("+");
					for (j = 0; j < Math.sqrt(tableu[i].length); j++) {
						if (i == Math.sqrt(N) || i == 0)
							System.out.print("---------+");

					}
					if (i == 0)
						System.out.println();

					for (j = 0; j < N; j++) {
						if (j == 0 || j == Math.sqrt(N) || j == N - 3)
							System.out.print("|");
						if (tableu[a][j] == 0)
							System.out.print(" . ");
						else if (tableu[a][j] < 0)
							System.out.print("(" + Math.abs(tableu[a][j]) + ")");
						else
							System.out.printf("%2d ", tableu[a][j]);
					}
					a++;
					System.out.println("|");
				}
			System.out.print("+");
			for (j = 0; j < Math.sqrt(N); j++) {
				System.out.print("---------+");
			}
			System.out.println();
		}

	}
}
