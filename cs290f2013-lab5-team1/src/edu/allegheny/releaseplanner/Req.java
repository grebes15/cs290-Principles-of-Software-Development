package edu.allegheny.releaseplanner;

public class Req implements Comparable<Req>
{
    double PperC;
    int num;

    public Req(double PperC, int num)
    {
        this.PperC = PperC;
        this.num = num;
    }

    public double getPperC()
    {
        return PperC;
    }

    public int getNum()
    {
        return num;
    }

    @Override
    public int compareTo(Req other)
    {
        
        if(this.num < other.getNum())
            return -1;
        else if(this.num > other.getNum())
            return 1;
        else
            return 0;
    }
}
