import java.util.Scanner;

public class TicTacToeGame {
    char[] allTokenData = new char[9];

    class Player {
        char playerToken;
        char[] tokenData = new char[9];
        String name;

        void setData(char data, Scanner sc) {
            sc = new Scanner(System.in);
            for (int i = 0; i < 9; i++) {
                tokenData[i] = 'N';
                allTokenData[i] = 'N';
            }
            sc.reset();
            System.out.println("\nEnter name:");
            sc.reset();
            name = sc.nextLine();
            playerToken = data;
        }

        void check() {
            Scanner sc = new Scanner(System.in);
            int n;

            while (true) {
                System.out.print("Enter number: ");
                n = sc.nextInt();

                if (n >= 1 && n <= 9) {
                    int index = n - 1;
                    if (tokenData[index] == 'N') {
                        allTokenData[index] = playerToken;
                        tokenData[index] = playerToken;
                        break;
                    } else {
                        System.out.println("\nEnter valid number.");
                    }
                } else {
                    System.out.println("\nEnter valid number.");
                }
            }
        }

        void flushData() {
            for (int i = 0; i < 9; i++) {
                tokenData[i] = 'N';
            }
        }

        boolean checkWin() {
            return checkRows() || checkColumns() || checkDiagonals();
        }

        boolean checkRows() {
            for (int i = 0; i < 9; i += 3) {
                if (tokenData[i] != 'N' && tokenData[i] == tokenData[i + 1] && tokenData[i] == tokenData[i + 2]) {
                    return true;
                }
            }
            return false;
        }

        boolean checkColumns() {
            for (int i = 0; i < 3; i++) {
                if (tokenData[i] != 'N' && tokenData[i] == tokenData[i + 3] && tokenData[i] == tokenData[i + 6]) {
                    return true;
                }
            }
            return false;
        }

        boolean checkDiagonals() {
            if (tokenData[0] != 'N' && tokenData[0] == tokenData[4] && tokenData[0] == tokenData[8]) {
                return true;
            }

            return tokenData[2] != 'N' && tokenData[2] == tokenData[4] && tokenData[2] == tokenData[6];
        }
    }

    void display(char[] c, String ply1, String ply2) {
        System.out.println("\n------------------- menu-----------------");
        System.out.println("_______________________\t\t\t\t_______________________");
        System.out.println("|                       |\t\t\t\t|                       |");
        System.out.println("|      enter choice     |\t\t\t\t|       GAME PAD        |");
        System.out.println("|_______________________|\t\t\t\t|_______________________|");
        System.out.println("|       |       |       |\t\t\t\t|       |       |       |");
        System.out.println("|   1   |   2   |   3   |\t\t\t\t|   " + c[0] + "   |   " + c[1] + "   |   " + c[2] + "   |");
        System.out.println("|_______|_______|_______|\t\t\t\t|_______|_______|_______|");
        System.out.println("|       |       |       |\t\t\t\t|       |       |       |");
        System.out.println("|   4   |   5   |   6   |\t\t\t\t|   " + c[3] + "   |   " + c[4] + "   |   " + c[5] + "   |");
        System.out.println("|_______|_______|_______|\t\t\t\t|_______|_______|_______|");
        System.out.println("|       |       |       |\t\t\t\t|       |       |       |");
        System.out.println("|   7   |   8   |   9   |\t\t\t\t|   " + c[6] + "   |   " + c[7] + "   |   " + c[8] + "   |");
        System.out.println("|_______|_______|_______|\t\t\t\t|_______|_______|_______|");
        System.out.println("player 1 = " + ply1 + " (token = X)");
        System.out.println("player 2 = " + ply2 + " (token = O)");
    }

    public static void main(String[] args) {
        TicTacToeGame ticTacToe = new TicTacToeGame();
        Player p1 = ticTacToe.new Player();
        Player p2 = ticTacToe.new Player();
        boolean playAgain = false;
        int check = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            int choice = 0;
            if (!playAgain) {
                System.out.println("\nEnter name of player 1:");
                p1.setData('X', sc);
                System.out.println("\nEnter name of player 2:");
                p2.setData('O', sc);
            }
            ticTacToe.display(p1.tokenData, p1.name, p2.name);
            for (int i = 1, j = 0; i <= 9; i++, j++) {
                if (i % 2 != 0) {
                    // PLAYER 1
                    p1.check();
                    ticTacToe.display(ticTacToe.allTokenData, p1.name, p2.name);
                    if (p1.checkWin()) {
                        check =1;
                        System.out.println("\n" + p1.name + " is the winner!");
                        break;
                    }
                } else {
                    // PLAYER 2
                    p2.check();
                    ticTacToe.display(ticTacToe.allTokenData, p1.name, p2.name);
                    if (p2.checkWin()) {
                        check =1;
                        System.out.println("\n" + p2.name + " is the winner!");
                        break;
                    }
                }
                ticTacToe.display(ticTacToe.allTokenData, p1.name, p2.name);
            }

    
            if (check == 0) {
                System.out.println("\nIt's a draw!");
            }

            // -----------------------menu---------------------------
            while (true) {
                System.out.println("\nPress 1 for starting a new game.");
                System.out.println("Press 2 for playing again.");
                System.out.println("Press 3 to exit.");
                choice = sc.nextInt();
                if (choice == 1) {
                    playAgain = false;
                    break;
                } else if (choice == 2) {
                    playAgain = true;
                    break;
                } else if (choice == 3) {
                    System.out.println("Exited.");
                    sc.close(); // Close the scanner before exiting the program
                    return;
                } else {
                    System.out.println("Enter a valid choice.");
                }
            }
        }
    }
}