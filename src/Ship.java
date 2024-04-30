public class Ship {
    private int [] xLocation;
    private int [] yLocation;
    private int numHits;

    //public String checkLife(int[] row, int[] col)
    //{
        //if (row == this.xlocation);
    //}

    public void setLocations(String name, int[] xLocations, int[] yLocations)
    {
        this.xLocation = xLocations;
        this.yLocation = yLocations;
    }

    if (n == 1)
                {
                        Xshop.add(x1);
                        Yshop.add(y1);
                }
                else if (n == 2)
                {
                        Xshop.add(x1);
                        Xshop.add(x2);
                        Yshop.add(y1);
                        Yshop.add(y2);

                }
                else
                {
                        if (x1 == x2)
                        {
                                for (int i = 0; i < n; i++)
                                {
                                        Xshop.add(x1);
                                }
                        }
                        else
                        {
                                for (int i = x1; i <= x2; i++)
                                {
                                        Xshop.add(i);
                                }
                        }

                        if (y1 == y2)
                        {
                                for (int i = 0; i < n; i++)
                                {
                                        Yshop.add(y1);
                                }
                        }
                        else
                        {
                                for (int i = y1; i <= y2; i++)
                                {
                                        Yshop.add(i);
                                }
                        }
                }

}
