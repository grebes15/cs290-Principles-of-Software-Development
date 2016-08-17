/**
 * @author Dibyo Mukherjee
 * The ReleasePlanner class holds a fixed cost and an array of requirements.
 * It runs a dynamic programming algorithm in the optimizer() method to select
 * the requirments to be included in the fixed cost.
 * It also sets various other variables that
 * @version 1.0.0
 * @since Sat, Oct 27, 2013
 */
package releaseplanner;
import java.util.ArrayList;

public class ReleasePlanner
{
  	private int c;
  	private Requirement[] requirements;
    private long runningTime;
    private int totalCost;
    private int totalBenefit;
    private int remainingBudget;
    private int profit;
    private ArrayList<Requirement> includes;
    private ArrayList<Requirement> discards;


    /**
     * Create a new ReleasePlanner object using a passed in fixedCost 
     * and an ArrayList of Requirements.
     * Converts ArrayList of requiremnts to an array of requirements 
     * The array is indexed from 1 instead of 0 since the optimizer()
     * method uses indexing from 1.
     *
     * @param fixedCost Total Fixed cost of requirements
     * @param requirements ArrayList of requirements
     * 
     */
    public ReleasePlanner(int fixedCost, ArrayList<Requirement> requirements)
    {
      this.c = fixedCost;
      requirements.add(0, new Requirement(0,0,0,"Null"));
      this.requirements = requirements.toArray(new Requirement[requirements.size()]);
      this.totalCost = 0;
      this.totalBenefit = 0;
      this.remainingBudget = 0;
      this.profit = 0;
      this.runningTime = 0;
      this.includes = new ArrayList<Requirement> ();
      this.discards = new ArrayList<Requirement> ();
    }

    /**
     * This method is an implementation of the dynamic programming
     * solution to the 0,1 Knapsack problem.
     * The keep attribute of a Requirement object is set to true at the 
     * end of the method if the object will be included in the solution.
     * 
     */
    public void optimizer()
    {
      long startTime = System.nanoTime();
      int opt[][] = new int[requirements.length][c +1];
      boolean sol[][] = new boolean[requirements.length + 1][c+1];
      for (int i = 1; i < requirements.length; i++)
      {
       for (int j = 1; j < c + 1; j++)
       {
         int option1 = opt[i-1][j];
         int option2 = Integer.MIN_VALUE;
         if (requirements[i].getCost() <= j) option2 = requirements[i].getBenefit() + opt[i-1][j-requirements[i].getCost()];
         opt[i][j] = Math.max(option1, option2);
         sol[i][j] = (option2 > option1);
       }
     }
      // determine which items to keep
     for (int n = requirements.length, w = c; n > 0; n--) 
     {
       if (sol[n][w]) {requirements[n].setKeep(true); w = w - requirements[n].getCost(); }
     }
     long endTime = System.nanoTime();
     runningTime = endTime - startTime;
    }    

   /**
     * This method sets values for the various output variables
     * required for the in-class demo
     */
   public void setOutputVariables()
    {
      for (int i = 1; i< requirements.length ; i++)
      {
      if (requirements[i].getKeep())
      {
        includes.add(requirements[i]);
        totalCost += requirements[i].getCost();
        totalBenefit += requirements[i].getBenefit();
      }
      else 
      {
        discards.add(requirements[i]);
      }
      }
      remainingBudget = c - totalCost;
      profit = totalBenefit - totalCost;
    }
     /**
     * This method call the setOutputVariables() method and then prints out 
     * the output.
     */
   public void output()
    {
      
      this.setOutputVariables();
      System.out.println("Total Cost: " + c + "\n");
      System.out.println("Elapsed Time: " + runningTime + "\n");
      System.out.println("Estimated Total Cost: $ " + totalCost + "\n");
      System.out.println("Estimated Total Benefit: $ " + totalBenefit + "\n");
      System.out.println("Remaining Budget: $" + remainingBudget+ "\n");
      System.out.println("Profit: $" + profit + "\n");
      System.out.println("List of includes: \n");
      System.out.println(includes.toString().replace(", ", "").replace("[", "").replace("]", ""));
      System.out.println("List of discarded: \n");
      System.out.println(discards.toString().replace(", ", "").replace("[", "").replace("]", ""));
    }

   /**
     * @return Total Fixed Cost
     */
   public int getFixedCost()
    {
      return c;
    }
    /**
     * @return Requirements
     */
    public Requirement[] getRequirements()
    {
      return requirements;
    }

    /**
     * @return Total Benefit
     */
    public int getTotalBenefit()
    {
      return totalBenefit;
    }

    /**
     * @return Total Cost
     */
    public int getTotalCost()
    {
      return totalCost;
    }

    /**
     * @return Total Profit
     */
    public int getProfit()
    {
      return profit;
    }

    /**
     * @return Remaining Budget
     */
    public int getRemainingBudget()
    {
      return remainingBudget;
    }
}