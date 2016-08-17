/****************************************************
Matthew Hajduk
Anton K
Brian Graham
Andreas 

Lab 4
Due: 10/9/13
****************************************************/
import java.util.ArrayList;

public class ListAlg<E>{
	

	protected ArrayList<E> inputList;
	protected ArrayList<ArrayList<E>> outputList = new ArrayList<ArrayList<E>>();
	protected E temp;
	protected ArrayList<E> tempList;

	public ListAlg(ArrayList<E> inputList_n){
	inputList = inputList_n;
	}

	public void setInputList(ArrayList ){

	}

	public ArrayList generateList(){
	
	
	this.temp = inputList.get(0);
	ArrayList tempList = new ArrayList();

		int inputLength = inputList.size();
		for(int i=0; i<(inputLength-1); i++){
			for(int k=i+1; k<(inputLength) ; k++){
			tempList = new ArrayList<E>();
			tempList = (ArrayList) inputList.clone();
			//System.out.println("tempList before:"+tempList.toString());	
			temp = tempList.get(i);
			//System.out.println("k:"+k);
			//System.out.println("i:"+i);
			//System.out.println("temp:"+temp);
			tempList.set(i,tempList.get(k));
			tempList.set(k,temp);
			//System.out.println(tempList.toString());	
			//System.out.println("outputList before:"+outputList.toString());			
			outputList.add((ArrayList) tempList.clone());
			System.out.println("outputList:"+outputList.toString());
				
				
			}
		}
	return outputList;

	}





}
