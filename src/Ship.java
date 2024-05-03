/**
 * @file Main.java
 * @brief The biggest issue was figuring out what methods we wanted this class to hold
 * @author Zachary Greene
 * @date: May 3, 2024
 * @acknowledgement: Hongyun Peng
 */

import java.util.ArrayList;

public class Ship {
        String name;

        // Setup for each of the important variables
        int xloc1, xloc2, yloc1, yloc2,n;


        public Ship(int x1, int y1, int x2, int y2, String name, int n) // Initializing all of the needed identifiers and values
        {
                this.n = n;
                this.xloc1 = x1;
                this.xloc2 = x2;
                this.yloc1 = y1;
                this.yloc2 = y2;
                this.name = name;

        }

        // Gets and returns the location of the ending and starting block for a ship
        public String getLocation()
        {
                String firstBlock = "Your ship has a starting block at the location of " + (xloc1+1) + ", " + (yloc1+1);
                String endBlock = " and your ship has an ending at " + (xloc2+1) + ", " + (yloc2+1);
                return firstBlock + endBlock;
        }
}
