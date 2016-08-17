/**
 * JC.java
 * This file is for setting up the parameters for JCommander and the flags it should recognize 
 * 
 * @author  Kara King
 */
package releaseplanner;
import com.beust.jcommander.*;
import java.util.*;
 public class JC {


    /**
     *-c is used for the total cost the user wants to use capital or lower case c is okay
     */
    @Parameter(names = { "-C","-c"}, description = "Total Fixed Cost")
  private int cost;
 
    /**
     *-rq is used for the user to input a listing of id, cost, benifit, and description for each requirement
     */

  @Parameter(names = "-rq", required = false, variableArity = true, description = "list of requirements and their features")
  private List<String> requirementL=new ArrayList<String>();

    /**
     *-file is used in command line for the user to input a csv file
     */

  @Parameter(names = "-file", description = "CSV File name")
  private String file;

    /**
    *Accessor for the list of requirements
     * @return  list of strings of the requirement features
     */
    public List<String> getRequirementList() {
        return requirementL;
    }

    /**
     * Accessor for the second position change
     * @return  the total cost
     */
    public int getCost() {
        return cost;
    }
    /**
     * Accessor for the file inputted
     * @return  the file the user wants to use
     */
    public String getFile() {
        return file;
    }

}
