/***************************************************
Matt Hajduk
Anton K
Brian Graham
Andreas L
****************************************************/

package ListAlg;

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.*;

public class ListAlgIO{

	public static void main(String[] ArrayInput){

	/*Scanner scan = new Scanner(System.in);
	
	List<E> = new ArrayList<E>;

	System.out.println("Enter yo shit");
	
	for(int i=0; i<n; i++)
	{
		list.add(scan.next());
	}
	
	String[] tt= {"1","2","3","4"};
	ArrayList<String> intList1 = new  ArrayList(Arrays.asList(tt));
	*/
	List commandLineTest = new ArrayList(Arrays.asList(ArrayInput));
	List tyrol = new ArrayList();
	List tyrolInput = new ArrayList();
	tyrolInput.add("T1");
	tyrolInput.add(23.3756);
	tyrolInput.add(10);
	tyrol.add(1);
	tyrol.add("baldib");
	tyrol.add(tyrolInput);
	tyrol.add(.234235);
	//ListAlg listAlg1 = new ListAlg(tyrol);
	System.out.println("List generated from command line inputs: " + ListAlg.generateList((ArrayList) commandLineTest));
	ArrayList<ArrayList> outputList = ListAlg.generateList((ArrayList) tyrol);
	System.out.println("List generated from 1 (as an int), 'baldib' (as a String), tyrolInput (as an ArrayList cosisnting of " +tyrolInput.toString() +") and .234235 (as a float) : " + outputList.toString());
	}
}
