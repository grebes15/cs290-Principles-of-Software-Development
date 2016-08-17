/**
 * @author Dibyo Mukherjee
 * Models a requirement that has a
 * Number, cost, benefit, and name
 *
 * @version 2.0.0
 * @since Sat, Oct 30, 2013
 */
package releaseplanner;


public class Requirement
{

    private int benefit;
    private int cost;
    private int requirementNum;
    private String requirementName; 
    private boolean keep;

     /**
     * Create a Requirement object  
     *
     * @param Requirement Number
     * @param Cost
     * @param Benefit
     * @param Requirement Name
     */
    public Requirement(int requirementNum, int cost, int benefit, String requirementName)
    {
	this.benefit = benefit;
	this.cost = cost;	
	this.requirementName = requirementName;
	this.requirementNum = requirementNum;
    }
	
    /**
     * @return Benefit
     */
    public int getBenefit()
    {return benefit;}
	
    /**
     * @return Cost
     */
    public int getCost()
    {return cost;}
	
    /**
     * @return Keep
     */
    public boolean getKeep()
    {
	return keep;
    }

    /**
     * @param True if requirement is to be included in solution. False otherwise.
     */
    public void setKeep(boolean inputKeep)
    {
	   keep=inputKeep;
    }

    /**
     * @return Requirement number, cost, benefit, requirement Name
     */
    public String toString()
    {
	return requirementNum + "\t" + cost + "\t" + benefit + "\t" + requirementName + "\n";
    }
}
