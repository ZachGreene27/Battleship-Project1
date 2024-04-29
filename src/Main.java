import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    final static int BOX_SIZE = 60;
    final static int WINDOW_SIZE1 = 600;
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
        DrawingPanel panel2 = new DrawingPanel(WINDOW_SIZE1, WINDOW_SIZE2);
        Graphics g = panel.getGraphics();
        Graphics m = panel2.getGraphics();
        drawBoard1(g);
        drawBoard2(m);
        System.out.println("Hi Player 1");
        System.out.println("Please create a ship of size 2.");
        setShipsLoc(board1,1);
        System.out.println("Please create a ship of size 2.");
        setShipsLoc(board1,2);
        System.out.println("Please create a ship of size 3.");
        setShipsLoc(board1,3);
        System.out.println("Please create a ship of size 3.");
        setShipsLoc(board1,4);
        System.out.println("Please create a ship of size 5.");
        setShipsLoc(board1,5);
        System.out.println("Hi Player 2");
        System.out.println("Please create a ship of size 2.");
        setShipsLoc(board1,1);
        System.out.println("Please create a ship of size 2.");
        setShipsLoc(board1,2);
        System.out.println("Please create a ship of size 3.");
        setShipsLoc(board1,3);
        System.out.println("Please create a ship of size 3.");
        setShipsLoc(board1,4);
        System.out.println("Please create a ship of size 5.");
        setShipsLoc(board1,5);
        System.out.println("moves = " + moves);
        //g.drawString(outputTurn(turn), 320, 500);
        //m.drawString(outputTurn(turn), 320, 500);
        // Set the callback method for event mouseClick
        panel.onMouseClick(
                (x, y) -> {
                    handleClick1(g, x, y);
                }
        );
        panel2.onMouseClick(
                (x, y) -> {
                    handleClick2(m, x, y);
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

    private static void handleClick1(Graphics g, int x, int y)
    //0 =empty; 1=floating piece; -1 = sunk, -2=miss
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
            g.fillRect(280, 620, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Miss!", 280, 650);
            g.drawString("Player 2 it's your turn!", 280, 670);
            board1[row][col] = -2;
            g.setColor(Color.BLUE);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        // 3) If there is a floating boat on the box, print hit
        if (board1[row][col] == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(280, 620, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Hit!", 280, 650);
            g.drawString("Player 2 it's your turn!", 280, 670);
            board1[row][col] = -1;
            g.setColor(Color.RED);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        if (isWinner(turn, board1) || isWinner(turn, board2)) {
            isGameOver = true;
            g.setColor(Color.WHITE);
            g.fillRect(280, 620, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Game over!", 280, 650);
            g.drawString("Player " + turn + " is the winner!!!", 300, 670);
        }

    }
    private static void handleClick2(Graphics m, int x, int y)
    //0 =empty; 1=floating piece; -1 = sunk, -2=miss
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
        if (board2[row][col] == 0) {
            m.setColor(Color.WHITE);
            m.fillRect(280, 620, 275, 175);
            m.setColor(Color.BLACK);
            m.drawString("Miss!", 280, 650);
            m.drawString("Player 1 it's your turn!", 280, 670);
            board2[row][col] = -2;
            m.setColor(Color.BLUE);
            m.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        // 3) If there is a floating boat on the box, print hit

        if (board2[row][col] == 1) {
            m.setColor(Color.WHITE);
            m.fillRect(280, 620, 275, 175);
            m.setColor(Color.BLACK);
            m.drawString("Hit!", 280, 650);
            m.drawString("Player 1 it's your turn!", 280, 670);
            board2[row][col] = -1;
            m.setColor(Color.RED);
            m.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        if (isWinner(turn, board1) || isWinner(turn, board2)) {
            isGameOver = true;
            m.setColor(Color.WHITE);
            m.fillRect(280, 650, 275, 175);
            m.setColor(Color.BLACK);
            m.drawString("Game over!", 280, 650);
            m.drawString("Player " + turn + " is the winner!!!", 300, 670);
        }

    }
    /**
     * changeTurn() changes value of turn from PLayer 1 to Player 2
     * and from Player 1 to PLayer 2
     */
    private static void changeTurn() {
        if (moves % 2 + 1 == 1) {
            turn = p2Turn;
        } else {
            turn = p1Turn;
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
        g.drawString("Hello Player 1", 260, 620);
    }

    private static void drawBoard2(Graphics m) {
        m.drawLine(0, 0, 0, 600);
        m.drawLine(60, 0, 60, 600);
        m.drawLine(120, 0, 120, 600);
        m.drawLine(180, 0, 180, 600);
        m.drawLine(240, 0, 240, 600);
        m.drawLine(300, 0, 300, 600);
        m.drawLine(360, 0, 360, 600);
        m.drawLine(420, 0, 420, 600);
        m.drawLine(480, 0, 480, 600);
        m.drawLine(540, 0, 540, 600);
        m.drawLine(600, 0, 600, 600);
        m.drawLine(0, 0, 600, 0);

        m.drawLine(0, 60, 600, 60);
        m.drawLine(0, 120, 600, 120);
        m.drawLine(0, 180, 600, 180);
        m.drawLine(0, 240, 600, 240);
        m.drawLine(0, 300, 600, 300);
        m.drawLine(0, 360, 600, 360);
        m.drawLine(0, 420, 600, 420);
        m.drawLine(0, 480, 600, 480);
        m.drawLine(0, 540, 600, 540);
        m.drawLine(0, 600, 600, 600);
        m.drawString("Hello Player 2", 260, 620);
    }


    /**
     * drawBoard()1 draws an empty board
     *
     * @param g : Graphics
     */

    /**
     * Outputs the correct turn based on which move we are currently on
     *
     * @param  : int placeholder for turn
     * @return : String allows us to return a string to put onto the screen where our board is
     */
    //public static String outputTurn(int n) {
    //if (n % 2 == 1) {
    //  return ("Player 1: Your turn");
    // } else
    // return ("Player 2: Your turn");
    //}

    public static void setShipsLoc(int board[][], int s) {
        Scanner scan = new Scanner(System.in);
            System.out.println("Where you want to set your ship" + s + "?");
            System.out.println("Please provide the x coordinate of the start point");
            int xlocS = scan.nextInt() - 1;
            System.out.println("and y coordinate");
            int ylocS = scan.nextInt() - 1;
            System.out.println("Please provide the x coordinate of the end point");
            int xlocE = scan.nextInt() - 1;
            System.out.println("and y coordinate");
            int ylocE = scan.nextInt() - 1;
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