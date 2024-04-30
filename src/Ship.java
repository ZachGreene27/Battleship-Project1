import java.util.ArrayList;

public class Ship {
        String name;

        int xloc1, xloc2, yloc1, yloc2,n;


        public Ship(int x1, int y1, int x2, int y2, String name, int n)
        {
                this.n = n;
                this.xloc1 = x1;
                this.xloc2 = x2;
                this.yloc1 = y1;
                this.yloc2 = y2;
                this.name = name;

        }
//
//        public void setShip(int n, int x1, int y1, int x2, int y2, String name)
//        {
//                if (n == 1)
//                {
//                        Xshop.add(x1);
//                        Yshop.add(y1);
//                }
//                else if (n == 2)
//                {
//                        Xshop.add(x1);
//                        Xshop.add(x2);
//                        Yshop.add(y1);
//                        Yshop.add(y2);
//
//                }
//                else
//                {
//                        if (x1 == x2)
//                        {
//                                for (int i = 0; i < n; i++)
//                                {
//                                        Xshop.add(x1);
//                                }
//                        }
//                        else
//                        {
//                                for (int i = x1; i <= x2; i++)
//                                {
//                                        Xshop.add(i);
//                                }
//                        }
//
//                        if (y1 == y2)
//                        {
//                                for (int i = 0; i < n; i++)
//                                {
//                                        Yshop.add(y1);
//                                }
//                        }
//                        else
//                        {
//                                for (int i = y1; i <= y2; i++)
//                                {
//                                        Yshop.add(i);
//                                }
//                        }
//                }
//        }

        public String getLocation()
        {
                String firstBlock = "Your ship has a starting block at the location of " + (xloc1+1) + ", " + (yloc1+1);
                String endBlock = " and your ship has an ending at " + (xloc2+1) + ", " + (yloc2+1);
                return firstBlock + endBlock;
        }
}
