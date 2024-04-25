import java.awt.*;

public class Main {
    final static int BOX_SIZE = 120;
    final static int WINDOW_SIZE1 = 1300;
    final static int WINDOW_SIZE2 = 600;
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
        board1 = new int [10][10];
        board2 = new int [10][10];

        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE1, WINDOW_SIZE2);
        Graphics g = panel.getGraphics();
        drawBoard1(g);
        drawBoard2(g);
        System.out.println("moves = " + moves);
        g.drawString(outputTurn(p1Turn),625,350);
        // Set the callback method for event mouseClick
        panel.onMouseClick(
                (x, y) -> {
                    handleClick(g, x, y);
                }
        );

    }

    /**
     * isWinner() receives 2 parameters: the current turn,
     *   and a reference to the board array. It should check
     *   if all possible references of a boat having a floating piece
     *   is true otherwise return false.
     * @param turn : int, current turn (Player 1 or Player 2)
     * @param board : int[][]
     * @return  boolean
     */
    private static boolean isWinner(int turn, int[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }
    private static void handleClick(Graphics g, int x, int y)
    {
        int row, col;
        // Obtain the row, col for the box clicked
        row = y / BOX_SIZE;
        col = x / BOX_SIZE;
        // Obtain the coordinate of the box
        int yTop = row * BOX_SIZE;
        int xTop = col * BOX_SIZE;
        // 1) If game is over then return back to main()
        if (isGameOver)
        {
            return;
        }
        // 2) If the box is occupied, then return
        if (board1[row][col] != 0  || board2[row][col] != 0)
            return;


        if (isWinner(turn,board1) || isWinner(turn,board2))
        {
            isGameOver = true;
            g.setColor(Color.WHITE);
            g.fillRect(625,325,675,375);
            g.setColor(Color.BLACK);
            g.drawString("Game over!",625,325);
            g.drawString("Player " + turn + " is the winner!!!",605,375);
        }

    }

    /**
     * changeTurn() changes value of turn from PLayer 1 to Player 2
     * and from Player 1 to PLayer 2
     */
    private static void changeTurn() {

        if (moves % 2 + 1 == 1)
        {
            turn = p1Turn;
        }
        else
        {
            turn = p2Turn;
        }

    }

    /**
     * drawBoard() draws an empty board
     * @param g : Graphics
     */
    private static void drawBoard1(Graphics g)
    {
        g.drawLine(0,0,0,300);
        g.drawLine(100,0,100,300);
        g.drawLine(200,0,200,300);
        g.drawLine(300,0,300,300);
        g.drawLine(0,0,300,0);
        g.drawLine(0,300,300,300);
        g.drawLine(0,100,300,100);
        g.drawLine(0,200,300,200);

        //for (int i = 0; i < )


    }

    /**
     * drawBoard() draws an empty board
     * @param g : Graphics
     */
    private static void drawBoard2(Graphics g)
    {
        g.drawLine(0,400,0,700);
        g.drawLine(100,400,100,700);
        g.drawLine(200,400,200,700);
        g.drawLine(300,400,300,700);
        g.drawLine(350,0,650,0);
        g.drawLine(350,300,650,300);
        g.drawLine(350,100,650,100);
        g.drawLine(350,200,650,200);
    }

    /**
     * Outputs the correct turn based on which move we are currently on
     * @param n : int placeholder for turn
     * @return : String allows us to return a string to put onto the screen where our board is
     */
    public static String outputTurn(int n)
    {
        if (n % 2 == 1)
        {
            return ("Player 1: Your turn");
        }
        else
            return ("Player 2: Your turn");
    }



}