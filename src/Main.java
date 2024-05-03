/**
 * @file Main.java
 * @brief The largest issue was in figuring out how to deal with the extra Ship class and implement it well
 * @author Zachary Greene
 * @date: May 3, 2024
 * @acknowledgement: Hongyun Peng
 */

import java.awt.*;
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
    public static int xlocS1,xlocS2,xlocS3, xlocS4,xlocS5;

    public static int ylocS1,ylocS2,ylocS3,ylocS4,ylocS5;

    public static int xlocE1,xlocE2,xlocE3,xlocE4,xlocE5;

    public static int ylocE1,ylocE2,ylocE3,ylocE4,ylocE5;

    public static int xlocS12,xlocS22,xlocS32,xlocS42,xlocS52;

    public static int ylocS12,ylocS22,ylocS32,ylocS42,ylocS52;

    public static int xlocE12,xlocE22,xlocE32,xlocE42,xlocE52;

    public static int ylocE12,ylocE22,ylocE32,ylocE42,ylocE52;

    private static Ship ship1,ship2,ship3,ship4,ship5,ship12,ship22,ship32,ship42,ship52;




    public static void main(String[] args) {
        // Set up some variables to discern turns
        moves = 0;
        turn = 1;
        isGameOver = false;
        // Set up the board for each player
        board1 = new int[10][10];
        board2 = new int[10][10];

        // Making the drawing panels and the graphics with which to draw
        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE1, WINDOW_SIZE2);
        DrawingPanel panel2 = new DrawingPanel(WINDOW_SIZE1, WINDOW_SIZE2);
        Graphics g = panel.getGraphics();
        Graphics m = panel2.getGraphics();
        drawBoard1(g);
        drawBoard2(m);
        // Asking for each location of each ship for player 1
        System.out.println("Hi Player 1");
        getShipsLoc1();
        setShipLoc(board1, xlocE1, xlocS1, ylocE1, ylocS1);
        getShipsLoc2();
        setShipLoc(board1, xlocE2, xlocS2, ylocE2, ylocS2);
        getShipsLoc3();
        setShipLoc(board1, xlocE3, xlocS3, ylocE3, ylocS3);
        getShipsLoc4();
        setShipLoc(board1, xlocE4, xlocS4, ylocE4, ylocS4);
        getShipsLoc5();
        setShipLoc(board1, xlocE5, xlocS5, ylocE5, ylocS5);

        // Initializing each ship into the ship class for player 1
         ship1 = new Ship(xlocS1,ylocS1,xlocE1,ylocE1,"Ship1",1);
         ship2 = new Ship(xlocS2,ylocS2,xlocE2,ylocE2,"Ship2",2);
         ship3 = new Ship(xlocS3,ylocS3,xlocE3,ylocE3,"Ship3",3);
         ship4 = new Ship(xlocS4,ylocS4,xlocE4,ylocE4,"Ship4",4);
         ship5 = new Ship(xlocS5,ylocS5,xlocE5,ylocE5,"Ship5",5);

         // Creating a seperation wall so player 1 can't see the placements for player 2
        for (int c= 0; c<6; c++) {
            System.out.println("/////////////////");
        }

        // Asking for each location of each ship for player 2
        System.out.println("Hi Player 2");
        getShipsLoc12();
        setShipLoc(board2, xlocE12, xlocS12, ylocE12, ylocS12);
        getShipsLoc22();
        setShipLoc(board2, xlocE22, xlocS22, ylocE22, ylocS22);
        getShipsLoc32();
        setShipLoc(board2, xlocE32, xlocS32, ylocE32, ylocS32);
        getShipsLoc42();
        setShipLoc(board2, xlocE42, xlocS42, ylocE42, ylocS42);
        getShipsLoc52();
        setShipLoc(board2, xlocE52, xlocS52, ylocE52, ylocS52);

        // Initializing each ship into the ship class for player 2
         ship12 = new Ship(xlocS12,ylocS12,xlocE12,ylocE12,"Ship12",6);
         ship22 = new Ship(xlocS22,ylocS22,xlocE22,ylocE22,"Ship22",7);
         ship32 = new Ship(xlocS32,ylocS32,xlocE32,ylocE32,"Ship32",8);
         ship42 = new Ship(xlocS42,ylocS42,xlocE42,ylocE42,"Ship42",9);
         ship52 = new Ship(xlocS52,ylocS52,xlocE52,ylocE52,"Ship52",10);

         //Final seperation area before starting the actual game
        for (int c= 0; c<6; c++) {
            System.out.println("/////////////////");
        }
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

        // Flip the turn
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
        // Iterates through the rows and columns of the board to check for any remaining floating pieces then
        // returns false if there are otherwise true
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Handles the click of the user to be able to allow for coordinates to be found and the correct shape drawn
     * @param g : Graphics, which allows for us to call upon the graphics type to draw onto our board
     * @param x : int, the x coordiante of our click
     * @param y : int, the y coordinate of our click
     */
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
        moves++;
        // 1) If game is over then return back to main()
        if (isGameOver) {
            return;
        }
        // 2) If the box is empty, print miss
        if (board1[row][col] == 0) {
            g.setColor(Color.WHITE);
            g.fillRect(280, 635, 275, 175);
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
            g.fillRect(280, 635, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Hit!", 280, 650);
            g.drawString("Player 2 it's your turn!", 280, 670);
            board1[row][col] = -1;
            g.setColor(Color.RED);
            g.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        // Check if there is a winner for either side to ensure accuracy
        if (isWinner(turn, board1) || isWinner(turn, board2)) {
            isGameOver = true;
            g.setColor(Color.WHITE);
            g.fillRect(200, 635, 275, 175);
            g.setColor(Color.BLACK);
            g.drawString("Game over!", 280, 650);
            g.drawString("Player " + turn + " is the winner!!!", 250, 670);
        }

        // Using the ship class if wanted by the player to find the position of one of their ships
        Scanner kb = new Scanner (System.in);
        System.out.println("Would you like to be reminded of the location of any ship? (y/n)");
        if(kb.next().equals("y"))
        {
            getPosition();
        }


    }
    /**
     * Handles the click of the user to be able to allow for coordinates to be found and the correct shape drawn
     * @param m : Graphics, which allows for us to call upon the graphics type to draw onto our board
     * @param x : int, the x coordiante of our click
     * @param y : int, the y coordinate of our click
     */
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
        moves++;
        // 1) If game is over then return back to main()
        if (isGameOver) {
            return;
        }
        // 2) If the box is empty, print miss
        if (board2[row][col] == 0) {
            m.setColor(Color.white);
            m.fillRect(280, 635, 275, 175);
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
            m.fillRect(280, 635, 275, 175);
            m.setColor(Color.BLACK);
            m.drawString("Hit!", 280, 650);
            m.drawString("Player 1 it's your turn!", 280, 670);
            board2[row][col] = -1;
            m.setColor(Color.RED);
            m.fillRect(xTop, yTop, BOX_SIZE, BOX_SIZE);
        }

        // Check if there is a winner for either side to ensure accuracy
        if (isWinner(turn, board1) || isWinner(turn, board2)) {
            isGameOver = true;
            m.setColor(Color.WHITE);
            m.fillRect(200, 635, 275, 175);
            m.setColor(Color.BLACK);
            m.drawString("Game over!", 280, 650);
            m.drawString("Player " + turn + " is the winner!!!", 250, 670);
        }

        // Using the ship class if wanted by the player to find the position of one of their ships
        Scanner kb = new Scanner (System.in);
        System.out.println("Would you like to be reminded of the location of any ship? (y/n)");
        if(kb.next().equals("y"))
        {
            getPosition();
        }

    }
    /**
     * changeTurn() changes value of turn from PLayer 1 to Player 2
     * and from Player 1 to PLayer 2
     */
    private static void changeTurn() {
       //If it is an odd turn making the turn for player 2 otherwise player 1
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
        // Draw the horizontal lines for player 1's board
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

        // Draw the vertical lines for player 1's board
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

    /**
     * drawBoard()2 draws an empty board
     *
     * @param m : Graphics
     */
    private static void drawBoard2(Graphics m) {
        // Draw the horizontal lines for player 2's board
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

        // Draw the vertical lines for player 2's board
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
     * Sets the ships location on the board given the ending and starting coordinates of each start and end piece
     * and building the ship
     * @param board : int [][], allows us to set values on the board
     * @param xlocE : int, denotes the x location of the end piece
     * @param xlocS : int, denotes the x location of the start piece
     * @param ylocE : int, denotes the y location of the end piece
     * @param ylocS : int, denotes the y location of the start piece
     */
    public static void setShipLoc(int board [][], int xlocE, int xlocS, int ylocE, int ylocS){
        // Checks if vertical from bottom up and fills in board
        if (xlocS == xlocE && ylocE > ylocS) {
            for (int i = ylocS; i <= ylocE; i++) {
                board[xlocE][i] = 1;
            }
        }
        else if (xlocS == xlocE && ylocE < ylocS) // Checks if vertical from top down and fills in board
        {
            for (int i = ylocE; i <= ylocS; i++) {
                board[xlocE][i] = 1;
            }
        }
        else if (ylocS == ylocE && xlocE > xlocS)  // Checks if horizontal and from right to left and fills in board
        {
            for (int i = xlocS; i <= xlocE; i++) {
                board[i][ylocE] = 1;
            }
        }
        else if (ylocS == ylocE && xlocE < xlocS) // Checks if horizontal and from left to right and fills in board
        {
            for (int i = xlocE; i <= xlocS; i++) {
                board[i][ylocE] = 1;
            }
        }
        else if( xlocE == xlocS) // Checks if only one piece and all is equal
        {
            board[xlocS][ylocS] = 1;
        }
        // Prints out the two array as a replica of the board
        for (int i = 0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                System.out.print(board[i][j]+ " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc1() {
        Scanner scan = new Scanner(System.in);
       // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 1 of size 1.");
        System.out.println("Where you want to set your ship 1?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS1 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS1 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE1 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE1 = scan.nextInt() - 1;

    }


    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc2() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 2 of size 2.");
        System.out.println("Where you want to set your ship 2?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS2 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS2 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE2 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE2 = scan.nextInt() - 1;

    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc3() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 3 of size 3.");
        System.out.println("Where you want to set your ship 3?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS3 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS3 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE3 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE3 = scan.nextInt() - 1;


    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc4() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 4 of size 4.");
        System.out.println("Where you want to set your ship 4?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS4 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS4 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE4 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE4 = scan.nextInt() - 1;

    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc5() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 5 of size 5.");
        System.out.println("Where you want to set your ship 5?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS5 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS5 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE5 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE5 = scan.nextInt() - 1;
    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc12() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 1 of size 1.");
        System.out.println("Where you want to set your ship 1?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS12 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS12 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE12 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE12 = scan.nextInt() - 1;
    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc22() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 2 of size 2.");
        System.out.println("Where you want to set your ship 2?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS22 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS22 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE22 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE22 = scan.nextInt() - 1;
    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc32() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 3 of size 3.");
        System.out.println("Where you want to set your ship 3?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS32 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS32 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE32 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE32 = scan.nextInt() - 1;

    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc42() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 4 of size 4.");
        System.out.println("Where you want to set your ship 4?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS42 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS42 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE42 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE42 = scan.nextInt() - 1;
    }

    /**
     * Asks the user for location and inputs each resulting input into variables to allow for transition into the
     * ship class to allow for use in other methods
     */
    public static void getShipsLoc52() {
        Scanner scan = new Scanner(System.in);
        // Asks for the coordinates of the ship from the user and sets it to each appropriate variable for placing
        // in the ship class
        System.out.println("Please create ship 5 of size 5.");
        System.out.println("Where you want to set your ship 5?");
        System.out.println("Please provide the x coordinate of the start point");
        xlocS52 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocS52 = scan.nextInt() - 1;
        System.out.println("Please provide the x coordinate of the end point");
        xlocE52 = scan.nextInt() - 1;
        System.out.println("and y coordinate");
        ylocE52 = scan.nextInt() - 1;
    }

    /**
    Allows for the usage of the ship class and ensures that whatever ship wants to be checked can be seen in
     another method
     */
    public static void getPosition()
    {
        String answer;
        Scanner kb = new Scanner (System.in);
        // Ask for user input on which ship they would like to check
        System.out.println("Which ship would you like to check? Please put the ship number. Player 1 your ships are 1-5 and Player 2 your ships are 6-10.");
        int n = kb.nextInt();
        // if else ladder to check on the user input and assign the appropriate ship
        if (n == 1)
        {
            System.out.println(ship1.getLocation());
        }
        else if (n == 2)
        {
            System.out.println(ship2.getLocation());
        }
        else if (n == 3)
        {
            System.out.println(ship3.getLocation());
        }
        else if (n == 4)
        {
            System.out.println(ship4.getLocation());
        }
        else if (n == 5)
        {
            System.out.println(ship5.getLocation());
        }
        else if (n == 6)
        {
            System.out.println(ship12.getLocation());
        }
        else if (n == 7)
        {
            System.out.println(ship22.getLocation());
        }
        else if (n == 8)
        {
            System.out.println(ship32.getLocation());
        }
        else if (n == 9)
        {
            System.out.println(ship42.getLocation());
        }
        else if (n == 10)
        {
            System.out.println(ship52.getLocation());
        }
        // Allows for another instance in a row
        System.out.println("Would you like to be reminded of the location of any ship? (y/n)");
        answer = kb.next();
        while(answer.equalsIgnoreCase("y")) // Repeats to ask and obtain the
            // same info while the user wants to use this option during the match
        {
            // Ask for user input on which ship they would like to check
            System.out.println("Which ship would you like to check? Please put the ship number. Player 1 your ships are 1-5 and Player 2 your ships are 6-10.");
            n = kb.nextInt();
            // if else ladder to check on the user input and assign the appropriate ship
            if (n == 1)
            {
                System.out.println(ship1.getLocation());
            }
            else if (n == 2)
            {
                System.out.println(ship2.getLocation());
            }
            else if (n == 3)
            {
                System.out.println(ship3.getLocation());
            }
            else if (n == 4)
            {
                System.out.println(ship4.getLocation());
            }
            else if (n == 5)
            {
                System.out.println(ship5.getLocation());
            }
            else if (n == 6)
            {
                System.out.println(ship12.getLocation());
            }
            else if (n == 7)
            {
                System.out.println(ship22.getLocation());
            }
            else if (n == 8)
            {
                System.out.println(ship32.getLocation());
            }
            else if (n == 9)
            {
                System.out.println(ship42.getLocation());
            }
            else if (n == 10)
            {
                System.out.println(ship52.getLocation());
            }
            if (answer.equalsIgnoreCase("n"))
            {
                break;
            }
            // Gives a break inbetween ships
            for (int c= 0; c<8; c++) {
                System.out.println("/////////////////");
            }
            // ALlows for repetition or to end the loop
            System.out.println("Would you like to be reminded of the location of any ship? (y/n)");
            answer = kb.next();
        }
    }
}
