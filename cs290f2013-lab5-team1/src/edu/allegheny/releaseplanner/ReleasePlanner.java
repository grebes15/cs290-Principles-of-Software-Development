package edu.allegheny.releaseplanner;
import java.util.*;
import com.beust.jcommander.*;
import au.com.bytecode.opencsv.*;
import java.io.*;

public class ReleasePlanner
{
    // List for Requirement numbers, Benfits, Costs, Requirement Descriptions,
    // and the result list.  'w' is the total cost allowed 
    static ArrayList<Integer> R, B, C, best;
    static ArrayList<String> RD;
    static int w;

    /**
     * Takes input in the form of a CSV file using the flags -C for total 
     * cost allowed and -file for the name of the file
     * Prints standardized output for each of the two algorithms implemented
     */
    public static void main(String[] args)
    {
    	Parameters p = new Parameters();
    	new JCommander(p, args);
    	
    	R = new ArrayList<Integer>();
    	C = new ArrayList<Integer>();
    	B = new ArrayList<Integer>();
    	RD = new ArrayList<String>();
    	w = p.w;
    	try
        {
        if(p.fileName != null)
        {
            try
            {
                // Read from CSV file
    	        CSVReader reader = new CSVReader(new FileReader(p.fileName));
    	        // Retrieve information from the file
    	        ArrayList<String[]> list = (ArrayList<String[]>) reader.readAll();
                // Parse the results and add them to the correct list
                for(int i = 0; i < list.size(); i++)
                {
                    R.add(Integer.parseInt(list.get(i)[0]));
                    C.add(Integer.parseInt(list.get(i)[1]));
                    B.add(Integer.parseInt(list.get(i)[2]));
                    RD.add(list.get(i)[3]);
            
                }
            }
            catch(FileNotFoundException e1)
            {
                System.out.println("File name not correct");
                System.exit(0);
            }
            catch(NumberFormatException e1)
            {
                System.out.println("CSV file does not conform to requirements");
                System.exit(0);
            }
            catch(Exception e1)
            {
                System.out.println("Exception in input ");
                e1.printStackTrace();
                System.exit(0);
            }
        }
        else
        {
            try
            {
                ArrayList<String> reqInput = (ArrayList<String>) p.req;
                int i = 0;
                while(i < reqInput.size())
                {
                    R.add(Integer.parseInt(reqInput.get(i++)));
                    C.add(Integer.parseInt(reqInput.get(i++)));
                    B.add(Integer.parseInt(reqInput.get(i++)));
                    RD.add(reqInput.get(i++));
                }
            }
            catch(NumberFormatException e1)
            {
                System.out.println("Input is improperly formatted");
                System.exit(0);
            }
            catch(Exception e1)
            {
                System.out.println("Exception in input ");
                e1.printStackTrace();
                System.exit(0);
            }
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception in input");
            e.printStackTrace();
            System.exit(0);
        }
        
        // Run each solver, recording the time, and print the output
        long pre = System.nanoTime();
        solverDFS();
        long post = System.nanoTime();
        System.out.println("Using breadth-first search algorithm\n");
        output(w, post - pre, weight(best), benefit(best), R, RD, best);
        pre = System.nanoTime();
        solverGreedy();
        post = System.nanoTime();
        System.out.println("\nUsing greedy search algorithm\n");
        output(w, post - pre, weight(best), benefit(best), R, RD, best);
    }

    /**
     * Runs the DFS algorithm with each possible first state
     */
    public static void solverDFS()
    {
        for(int i = 0; i < R.size(); i++)
        {
            DFS(new ArrayList<Integer>(R.size()), i);
        }
    }

    /**
     * Determines the best possible list of requirements to optimize profit
     * given total cost constraint by iterating through each legal coombination
     * of requirements recursively and storing the best (highest profit) of those.
     * This algorithm will always generate the right solution, and is relatively fast
     * for small input lists, but scales very poorly.  It quickly becomes unfeasibly 
     * inefficient as the input size increases.
     * 
     * @param curR List of requirements in the state to be checked
     * @param i Next requirement to try adding
     */
    public static  void DFS(ArrayList<Integer> curR, int i)
    { 
        if(curR.size() >= R.size())
        {
            return;
        }
        else
        {
            curR.add(R.get(i));
            if(weight(curR) > w)
                return;
            if(best == null)
                best = (ArrayList<Integer>) curR.clone();
            if((benefit(curR) - weight(curR)) >= (benefit(best) - weight(best)))
            {
                best = (ArrayList<Integer>) curR.clone();
            }
        }
        for(int j = i + 1; j < R.size(); j++)
        {
            DFS((ArrayList<Integer>) curR.clone(), j);
        }
    }

    /**
     * Calculates the total cost of a list of requirements
     *
     * @param L A list of requirements
     * @return int representing the total cost of the list
     */
    public static int weight(ArrayList<Integer> L)
    {
        int sum = 0;
        if(L.size() == 0)
            return 0;
        for(int i = 0; i < L.size(); i++)
            sum += C.get((int) L.get(i));
        return sum;
    }

    /**
     * Calculates the total benefit of a list of requirements
     *
     * @param L A list of requirements
     * @return int representing the total benefit of the list
     */
    public static int benefit(ArrayList<Integer> L)
    {
        int sum = 0;
        for(int i = 0; i < L.size(); i++)
            sum += B.get((int) L.get(i));
        return sum;
    }

    /**
     * Prints the output in the standardized format
     *
     * @param totalCost The total cost allowed
     * @param time The time used
     * @param costUsed Total cost used in solution
     * @param benefitUsed Total benefit of the solution
     * @param req List of all requirement numbers
     * @param reqDesc Descriptions of all requirements
     * @param reqUsed List of requirements used in the solution
     */
    public static void output(int totalCost, long time, int costUsed, int benefitUsed, 
            ArrayList<Integer> req, ArrayList<String> reqDesc, ArrayList<Integer> reqUsed)
    {
        int costLeft = totalCost - costUsed;
        System.out.printf("Total Cost: $%d%n", totalCost);
        System.out.printf("Elapsed Time: %d%n", (int) time);
        System.out.printf("Estimated total cost: $%d%n", costUsed);
        System.out.printf("Estimated total benefit: $%d%n", benefitUsed);
        System.out.printf("Remaining flex budget: $%d%n", costLeft);
        System.out.printf("Profit: $%d%n", benefitUsed - costUsed);
        System.out.printf("List of includes:%n");
        for(int i = 0; i < reqUsed.size(); i++)
        {
            System.out.printf("\t %d) \"%s\"%n", req.get(reqUsed.get(i)), reqDesc.get(reqUsed.get(i)));
        }
        System.out.printf("List of discarded:%n");
        boolean check = true;
        // Determine which requirements weren't used
        for(int i = 0; i < req.size(); i++)
        {
            for(int j = 0; j < reqUsed.size(); j++)
            {
                if(req.get(i) == reqUsed.get(j))
                    check = false;
            }
            if(check)
            {
                System.out.printf("\t %d) \"%s\"%n", req.get(i), reqDesc.get(i));
            }
            check = true;
        }
    }

    /**
     * Estimates the best possible list of requirements to optimize profit
     * given total cost constraint by sorting the requirements in order of profit
     * per cost, then adding them to the result list in that order; profit per cost
     * is the heuristic for this solver.  This solution will not always generate the 
     * best possible solution, but it will find a good solution.  As the size of the
     * input list increases, this solution will become more accurate and more efficient.
     * For smaller inputs, this solution will be relatively slow (because of the sort 
     * algorithm) but it scales far better than the DFS.
     */
    public static void solverGreedy()
    {
        Req[] PperC = new Req[R.size()];
        for(int i = 0; i < PperC.length; i++)
        {
            PperC[i] = new Req((double) (B.get(i) - C.get(i)) * 1.0 / (C.get(i) * 1.0), i);
        }
        Arrays.sort(PperC);
        best = new ArrayList<Integer>(R.size());
        int costUsed = 0;
        for(int i = 0; i < PperC.length; i++)
        {
            if(costUsed + C.get(i) <= w && B.get(i) - C.get(i) >= 0)
                best.add(R.get(PperC[i].getNum()));
        }
    }
}
