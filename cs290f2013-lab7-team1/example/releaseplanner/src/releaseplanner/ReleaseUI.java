/**
 * @author Kara King
 * This is the user interface where the user can input a total cost via command line along
 * with a csvfile or a list of requirements. -file is to specify a file, -C is to specify the total
 * cost and -rq is to specify a list of requirements
 * 
 * @version 1.0.0
 * @since Sat, Oct 26, 2013
 */
package releaseplanner;
import java.util.*;
import au.com.bytecode.opencsv.*;
import java.io.*;
import java.lang.Object;
import java.text.DecimalFormat;
import com.beust.jcommander.*;

public class ReleaseUI
{
	/**
     *The main method calls JCommander to parse command line
     * arguements. It then makes a ReleasePlanner class, calls the 
     * optimizer method and then prints out the output.
     *
     * @param argv
     * @throws FileNotFoundException
     * @throws IOException
     */

	public static void main (String[] argv) throws FileNotFoundException, IOException
	{
		JC jct = new JC();   
		new JCommander(jct, argv);
		ArrayList<Requirement> requirements = new ArrayList<Requirement>();
        int idR=0;//keeps track of the requirement id number
        int costR=0;//keeps track of the requirement cost
        int benifitR=0;//keeps track of the requirement benifit
        int idp=0;//keeps track of the i value for the requirement id
        int benifitp=0;//keeps track of the i value for the benifit

        //Checks to see if the user is inputting a csv file or if they are putting the requirements through command line
        if(jct.getFile() ==null )
	    {	//this goes through the list of requirement features and separates cost from benefit from id from description
	    	List<String> getReq = jct.getRequirementList();
	    	for(int i =0;i < getReq.size()-1; i++)
	    	{
			//this is description
	    		if(((i+1)%4) == 0)
	    		{
	    			String reqDes = getReq.get(i);
	    			Requirement temp = new Requirement(idR, costR, benifitR, reqDes);
	    			requirements.add(temp);
	    		}
			//this is cost
	    		else if(((i+1)%2) == 0)
	    		{
	    			costR = Integer.parseInt(getReq.get(i));
	    		}
	    		else
	    		{
				//first requirement id
	    			if(i==0)
	    			{
	    				idR = Integer.parseInt(getReq.get(i));
	    				idp=i;
	    			}
				//first requirement benifit
	    			else if(i==2)
	    			{
	    				benifitR = Integer.parseInt(getReq.get(i));
	    				benifitp=i;
	    			}
	    			else
	    			{
					//checks to see if there are more id's read than benifits
	    				if(idp<benifitp)
	    				{
						//System.out.println(idR);
	    					idR = Integer.parseInt(getReq.get(i));
	    					idp=i;
	    				}
	    				else
	    				{
	    					benifitR = Integer.parseInt(getReq.get(i));
	    					benifitp=i;
	    				}
	    			}
	    		}
	    	}
	    }
        //reads the csv file if the user wants to input a file
	    else
	    {
	    	requirements = csvRead(jct.getFile());
	    }

        //creates a releaseplanner object
	    ReleasePlanner rp = new ReleasePlanner(jct.getCost(), requirements);
        rp.optimizer();//finds the optimal set of requirements
        rp.output();
    }

    /**
     *This method reads through a csvfile and parses it into features for a requirement
     *
     * @param fileName
     * @return an arraylist of requirements
     */
    public static ArrayList<Requirement> csvRead(String fileName)
    {
    	ArrayList<Requirement> requirements = new ArrayList<Requirement>();
    	try 
    	{   
			CSVReader reader = new CSVReader(new FileReader(fileName));  //error here cause i need to download binari
			String [] nextLine = reader.readNext();
			while((nextLine = reader.readNext()) != null)
			{
				// boolean parsable = true;
				// try
				// {
				// 	Integer.parseInt(nextLine[0]);

				// }catch(NumberFormatException e)
				// {
				// 	parsable = false;
				// }
			//if(parsable==true)
				//{
				int requirementNumber =Integer.parseInt(nextLine[0]);
				int cost = Integer.parseInt(nextLine[1]);
				int benefit = Integer.parseInt(nextLine[2]);
				String requirementName = nextLine[3];
				Requirement temporary = new Requirement(requirementNumber, cost, benefit, requirementName);
				requirements.add(temporary);
				//}
			}
		}
		//catches all of the exceptions
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return requirements;
	}
}
