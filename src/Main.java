import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    final static int BOX_SIZE = 60;
    final static int WINDOW_SIZE1 = 1300;
    final static int WINDOW_SIZE2 = 700;
    private static int moves;
    private static int turn;
    private static final int p1Turn = 1;
    private static final int p2Turn = 2;
    private static int[][] board1;
    private static int[][] board2;
    private static boolean isGameOver;

    public static void main(String[] args) {
        moves = 0;
        turn = 1;
        isGameOver = false;
        board1 = new int[10][10];
        board2 = new int[10][10];


        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE1, WINDOW_SIZE2);
        Graphics g = panel.getGraphics();
        drawBoard1(g);
        drawBoard2(g);
        System.out.println("Hi Player 1");
        setShipsLoc(board1);
        System.out.println("Hi Player 2");
        setShipsLoc(board2);
        System.out.println("moves = " + moves);
        g.drawString(outputTurn(p1Turn), 625, 635);
        // Set the callback method for event mouseClick
        panel.onMouseClick(
                (x, y) -> {
                    handleClick(g, x, y);
                }
        );
        changeTurn();

    }

    /**
     * isWinner() receives 2 parameters: the current turn,
     * and a reference to the board array. It should check
     * if all possible references of a boat having a floating piece
     * is true otherwise return false.
     *
     * @param turn  : int, current turn (Player 1 or Player 2)
     * @param board : int[][]
     * @return boolean
     */
    private static boolean isWinner(int turn, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void handleClick(Graphics g, int x, int y)//0 =empty; 1=floating piece; -1 = sunk
    //-2=miss
    {
        int row, col;
        // Obtain the row, col for the box clicked
        row = y / BOX_SIZE;
        col = x / BOX_SIZE;
        // Obtain the coordinate of the box
        int yTop = row * BOX_SIZE;
        int xTop = col * BOX_SIZE;
        // 1) If game is over then return back to main()
        if (isGameOver) {
            return;
        }
        // 2) If the box is empty, print miss
        if (board1[row][col] == 0) {
            g.setColor(Color.WHITE);
            g.fillRect(560, 603, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Miss!", 625, 635);
            board1[row][col] = -2;
            g.setColor(Color.BLUE);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        if (board2[row][col] == 0) {
            g.setColor(Color.WHITE);
            g.fillRect(560, 603, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Miss!", 625, 635);
            board2[row][col] = -2;
            g.setColor(Color.BLUE);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        // 3) If there is a floating boat on the box, print hit
        if (board1[row][col] == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(560, 603, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Hit!", 625, 635);
            board1[row][col] = -1;
            g.setColor(Color.RED);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }
        if (board2[row][col] == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(560, 603, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Hit!", 625, 635);
            board2[row][col] = -1;
            g.setColor(Color.RED);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        if (isWinner(turn, board1) || isWinner(turn, board2)) {
            isGameOver = true;
            g.setColor(Color.WHITE);
            g.fillRect(560, 603, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Game over!", 625, 635);
            g.drawString("Player " + turn + " is the winner!!!", 605, 655);
        }

    }

    /**
     * changeTurn() changes value of turn from PLayer 1 to Player 2
     * and from Player 1 to PLayer 2
     */
    private static void changeTurn() {

        if (moves % 2 + 1 == 1) {
            turn = p1Turn;
        } else {
            turn = p2Turn;
        }

    }

    /**
     * drawBoard()1 draws an empty board
     *
     * @param g : Graphics
     */
    private static void drawBoard1(Graphics g) {
        g.drawLine(0, 0, 0, 600);
        g.drawLine(60, 0, 60, 600);
        g.drawLine(120, 0, 120, 600);
        g.drawLine(180, 0, 180, 600);
        g.drawLine(240, 0, 240, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(360, 0, 360, 600);
        g.drawLine(420, 0, 420, 600);
        g.drawLine(480, 0, 480, 600);
        g.drawLine(540, 0, 540, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(0, 0, 600, 0);

        g.drawLine(0, 60, 600, 60);
        g.drawLine(0, 120, 600, 120);
        g.drawLine(0, 180, 600, 180);
        g.drawLine(0, 240, 600, 240);
        g.drawLine(0, 300, 600, 300);
        g.drawLine(0, 360, 600, 360);
        g.drawLine(0, 420, 600, 420);
        g.drawLine(0, 480, 600, 480);
        g.drawLine(0, 540, 600, 540);
        g.drawLine(0, 600, 600, 600);
    }

    /**
     * drawBoard()1 draws an empty board
     *
     * @param g : Graphics
     */
    private static void drawBoard2(Graphics g) {
        g.drawLine(700, 0, 700, 600);
        g.drawLine(760, 0, 760, 600);
        g.drawLine(820, 0, 820, 600);
        g.drawLine(880, 0, 880, 600);
        g.drawLine(940, 0, 940, 600);
        g.drawLine(1000, 0, 1000, 600);
        g.drawLine(1060, 0, 1060, 600);
        g.drawLine(1120, 0, 1120, 600);
        g.drawLine(1180, 0, 1180, 600);
        g.drawLine(1240, 0, 1240, 600);
        g.drawLine(1300, 0, 1300, 600);

        g.drawLine(700, 0, 1300, 0);
        g.drawLine(700, 60, 1300, 60);
        g.drawLine(700, 120, 1300, 120);
        g.drawLine(700, 180, 1300, 180);
        g.drawLine(700, 240, 1300, 240);
        g.drawLine(700, 300, 1300, 300);
        g.drawLine(700, 360, 1300, 360);
        g.drawLine(700, 420, 1300, 420);
        g.drawLine(700, 480, 1300, 480);
        g.drawLine(700, 540, 1300, 540);
        g.drawLine(700, 600, 1300, 600);
    }

    /**
     * Outputs the correct turn based on which move we are currently on
     *
     * @param n : int placeholder for turn
     * @return : String allows us to return a string to put onto the screen where our board is
     */
    public static String outputTurn(int n) {
        if (n % 2 == 1) {
            return ("Player 1: Your turn");
        } else
            return ("Player 2: Your turn");
    }

    public static void setShipsLoc(int board[][]) {
        Scanner scan = new Scanner(System.in);
            for (int s = 1; s <= 3; s++) {
                System.out.println("Where you want to set your ship" + s + "?");
                System.out.println("Please provide the x coordinate of the start point");
                int xlocS = scan.nextInt();
                System.out.println("and y coordinate");
                int ylocS = scan.nextInt();
                System.out.println("Please provide the x coordinate of the end point");
                int xlocE = scan.nextInt();
                System.out.println("and y coordinate");
                int ylocE = scan.nextInt();
                if (xlocS == xlocE && ylocE > ylocS) {
                    for (int i = ylocS; i <= ylocE; i++) {
                        board[xlocE][i] = 1;
                    }
                } else if (xlocS == xlocE && ylocE < ylocS) {
                    for (int i = ylocE; i <= ylocS; i++) {
                        board[xlocE][i] = 1;
                    }
                } else if (ylocS == ylocE && xlocE > xlocS) {
                    for (int i = xlocS; i <= xlocE; i++) {
                        board[i][ylocE] = 1;
                    }
                } else if (ylocS == ylocE && xlocE < xlocS) {
                    for (int i = xlocE; i <= xlocS; i++) {
                        board[i][ylocE] = 1;
                    }
                }
                for (int i = 0; i<board.length; i++) {
                    for (int j=0; j<board.length; j++) {
                        System.out.print(board[i][j]+ " ");
                    }
                    System.out.println(" ");
                }

            }
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");
        System.out.println("/////////////////");

    }
}