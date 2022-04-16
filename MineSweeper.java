import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    Scanner scan = new Scanner(System.in);
    private final int rows, columns, minesCount;
    String[][] space;
    String[][] mines;

    public MineSweeper() {
        System.out.println("Please enter the rows = ");
        rows = scan.nextInt();
        System.out.println("Please enter the columns = ");
        columns = scan.nextInt();

        minesCount = (rows*columns) / 4;
        space = new String[rows][columns];
        mines = new String[rows][columns];
        createSpaces();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mines[i][j] = "-";
            }
        }
        createMines(minesCount);

    }

    public void printSpaces() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(space[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public void printMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(mines[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public void createSpaces() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                space[i][j] = "-";
            }
        }

    }

    public void createMines(int minesCount) {

        for (int i = 0; i < minesCount; i++) {
            Random rand = new Random();
            int row = rand.nextInt(rows);
            int column = rand.nextInt(columns);

            if (!(mines[row][column].equals("*"))) {
                mines[row][column] = "*";
            } else {
                createMines(minesCount-i);
                break;
            }
        }

    }

    public String countMinesAround(int row,int column) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((row + i < 0) || (column + j < 0)) {
                    continue;
                } if ((row + i > rows-1) || (column + j > columns-1)) {
                    continue;
                } if (i == 0 && j == 0) {
                    continue;
                } if (mines[row+i][column+j].equals("*")) {
                    count++;
                }
            }
        }

        return String.valueOf(count);
    }

    public void start() {
        while (true) {
            int flag = 0;
            printSpaces();
            System.out.println("Please enter the row = ");
            int row = scan.nextInt();
            System.out.println("Please enter the column = ");
            int column = scan.nextInt();
            if (row > rows-1 || column > columns-1) {
                System.out.println("Your values are out of range. Please enter other values.");
                continue;
            }
            if (!(space[row][column].equals("*") || space[row][column].equals("-"))) {
                System.out.println("You already tried that one!");
                continue;
            }

            if (mines[row][column].equals("*")) {
                System.out.println("You lost!\n");
                printMines();
                break;
            } else {
                space[row][column] = countMinesAround(row,column);
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (!(space[i][j].equals("-"))) {
                        flag++;
                    }
                }
            }

            if (flag == (rows*columns)-minesCount ) {
                System.out.println("You won!\n");
                printMines();
                break;
            }

        }

    }

}
