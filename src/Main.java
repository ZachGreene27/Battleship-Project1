import java.awt.*;

public class Main {
    final static int BOX_SIZE = 100;
    final static int WINDOW_SIZE = 3 * (BOX_SIZE) + 103;
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

        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE, WINDOW_SIZE);
        Graphics g = panel.getGraphics();
        drawBoard1(g);
        drawBoard2(g);
        System.out.println("moves = " + moves);
        g.drawString(outputTurn(p1Turn),100,350);
        // Set the callback method for event mouseClick
        panel.onMouseClick(
                (x, y) -> {
                    handleClick(g, x, y);
                }
        );

    }

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

    }

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


    }

    private static void drawBoard2(Graphics g)
    {
        g.drawLine(0,350,0,650);
        g.drawLine(100,350,100,650);
        g.drawLine(200,350,200,650);
        g.drawLine(300,350,300,650);
        g.drawLine(350,0,650,0);
        g.drawLine(350,300,650,300);
        g.drawLine(350,100,650,100);
        g.drawLine(350,200,650,200);
    }
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