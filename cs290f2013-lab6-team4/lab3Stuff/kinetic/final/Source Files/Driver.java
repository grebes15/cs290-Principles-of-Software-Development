package edu.allegheny.kinetic;

/**
 *  Driver class that holds main method to run Kenitic.  Takes in the command line args and immediately processes the kinetic value.  If 
 *  the inputs are either Letters or any other non valid symbols it will throw an error.
 * @author Adam Wechter, Lorde and Champion of the Day (Dayman(aAAaaaaa))
 */
public class Driver{
    /**
     * @param args  Command line arguments, string array of arguments, converted into integers by parse int
     */
    public static void main(String [] args){
        try{
            System.out.println(Kinetic.computeVelocity(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        }catch(NumberFormatException e){System.out.println("Not a number");}
    }
}
