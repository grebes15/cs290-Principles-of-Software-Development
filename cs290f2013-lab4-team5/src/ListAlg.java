/****************************************************
Matthew Hajduk
Anton K
Brian Graham
Andreas 

Lab 4
Due: 10/9/13
****************************************************/
package ListAlg;

import java.util.List;
import java.util.ArrayList;

public class ListAlg{
	
/*
	protected List<E> inputList;
	protected List<List<E>> outputList = new List<List<E>>();
	protected E temp;
	protected List<E> tempList;
*/
	//public ListAlg(List<E> inputList_n){
	//inputList = inputList_n;
	//}




	public static ArrayList generateList (ArrayList inputList){
	
	
	ArrayList temp = new ArrayList();
	ArrayList tempList = new ArrayList();
	ArrayList outputList = new ArrayList();

		int inputLength = inputList.size();
		for(int i=0; i<(inputLength-1); i++){
			for(int k=i+1; k<(inputLength) ; k++){
			tempList = (ArrayList) inputList.clone();
			//System.out.println("tempList before:"+tempList.toString());	
			temp.add(tempList.get(i));
			//System.out.println("k:"+k);
			//System.out.println("i:"+i);
			//System.out.println("temp:"+temp);
			tempList.set(i,tempList.get(k));
			tempList.set(k,temp.get(0));
			temp.remove(0);
			//System.out.println(tempList.toString());	
			//System.out.println("outputList before:"+outputList.toString());			
			outputList.add((ArrayList) tempList.clone());
			tempList.clear();
			//System.out.println("outputList:"+outputList.toString());
				
				
			}
		}
	return outputList;

	}





}
