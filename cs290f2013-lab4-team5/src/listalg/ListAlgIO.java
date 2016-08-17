/***************************************************
Matt Hajduk
Anton K
Brian Graham
Andreas L
****************************************************/

package listalg;

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.*;

public class ListAlgIO{

	public static void main(String[] ArrayInput){

	List commandLineTest = new ArrayList(Arrays.asList(ArrayInput));
	System.out.println("List generated from command line inputs: " + ListAlg.generateList((ArrayList) commandLineTest));
	}
}
