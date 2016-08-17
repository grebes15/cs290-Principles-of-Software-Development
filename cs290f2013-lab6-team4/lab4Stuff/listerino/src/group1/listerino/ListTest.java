package group1.listerino;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*
 * Error Tests, tested by the coverage tests to make sure all areas are covered.  Names are all descriptive as to what each method Tests
 *
 * @author 
 */
public class ListTest {

     Listerino listerino = new Listerino();
     private ArrayList<ArrayList<Object>> arrList;
     private ArrayList<Object> aList;
     private ArrayList<Object> bList;
  

//-----------------Listerino.class Tests-----------------------   
//---------------------viewListArr----------------------------- 
	@Test
    public void viewOccupiedListArr() {
	arrList = new ArrayList<ArrayList<Object>>();
	aList = new ArrayList<Object>();
	aList.add(0,"cat");
	aList.add(1,"dog");
	aList.add(2,"horse");
	aList.add(3,"cow");
	arrList.add(aList);
	String expected = new String("cat, dog, horse, cow,\n");
	String actual = Listerino.viewListArr(arrList);
   }
//------------------------start-------------------------------
	@Test
    public void start() {
	Listerino.start();
    }
//---------------------viewListObj----------------------------
	@Test
    public void viewOccupiedListObj() {
	aList = new ArrayList<Object>();
	aList.add(0,"cat");
	aList.add(1,"dog");
	aList.add(2,"horse");
	aList.add(3,"cow");
	String expected = new String("cat, dog, horse, cow,\n");
	String actual = Listerino.viewListObj(aList);
    }
//--------------------checkHistory-----------------------------
//Returns Failed as intended.  Null list cannot be asserted as Equals	
	@Test
    public void checkHistory() {
	String actual = Listerino.checkHistory();
	assertEquals('\0', actual);
    }
//--------------------writeDescription-------------------------
	@Test
    public void writeDescription() {
	aList = new ArrayList<Object>();
	aList.add("boy");
	String actual = Listerino.writeDescription(aList);
	assertEquals("boy", actual);
    }
//Returns Failed as intended.  Null list cannot be asserted as Equals
	@Test
    public void writeDescriptionNull() {
	aList = new ArrayList<Object>();
	String actual = Listerino.writeDescription(aList);
	assertEquals('\0' , actual);
    }
//-----------------------listIn----------------------------------
	@Test
    public void listInNoComment(){
	aList = new ArrayList<Object>();
	aList.add("boy");
	aList.add("girl");
	Listerino.listIn(aList,"");
    }

	@Test
    public void listInEmptyList(){
	aList = new ArrayList<Object>();
	aList.add(0);
	Listerino.listIn(aList,"Empty");
    }	
//---------------------listInteract------------------------------
	@Test
    public void listInteract() {
	arrList = new ArrayList<ArrayList<Object>>();
	aList = new ArrayList<Object>();
	aList.add("boy");
	arrList.add(aList);
	ArrayList<Object> actual = Listerino.listInteract(arrList, 0);
	assertEquals(aList, actual);
    }
//----------------------permutate--------------------------------
	@Test
    public void permutateSingle() {
	ArrayList<ArrayList<Object>> expected = new ArrayList<ArrayList<Object>>();
	aList = new ArrayList<Object>();
	bList = new ArrayList<Object>();
	aList.add("boy");
	aList.add("girl");
	bList.add("girl");
	bList.add("boy");
	expected.add(aList);
	expected.add(bList);
	ArrayList<ArrayList<Object>> actual = Listerino.permutate(aList);
	assertEquals(expected, actual);
    }
//----------------------addElement---------------------------------
	@Test
    public void addElement() {
	aList = new ArrayList<Object>();
	Listerino.addElement(aList, "boy");
	assertEquals(1, aList.size());
    }
//------------------Cache.class Tests-------------------------------
//----------------------setTable------------------------------------
//	@Test
//   public void setTable() {
//	String expected = new String (" ");
//	Cache.setTable();
//    }
//-----------------------lookUp-------------------------------------
	@Test
     public void lookUp() {
	int expected = 0;
	int actual = Cache.lookUp("2");
	assertEquals(expected, actual);
    }
//--------------------getLocation-----------------------------------
//Returns Failed as intended.  Null list cannot be asserted as Equals
	@Test
    public void getLocation() {

	String actual = Cache.getLocation(0);
	assertEquals("\0", actual);
    }
//--------------------setNewEntry-----------------------------------
	@Test
    public void setNewEntry() {
	Boolean actual = Cache.setNewEntry("boy", "people");
	assertEquals(false, actual);
    }
//---------------------getHistory-----------------------------------
//Need actual file to finish test cases
//	@Test
//    public void getHistory() {
//	
//    }
//----------------------saveList------------------------------------
	@Test
    public void saveList() {
	arrList= new ArrayList<ArrayList<Object>>();
	aList = new ArrayList<Object>();
	aList.add("boy");
	aList.add("girl");
	arrList.add(aList);
	Cache.saveList("foo", arrList);
    }
//-----------------------getList--------------------------------------
	@Test
    public void getList() {
	ArrayList<ArrayList<Object>> actual = new ArrayList<ArrayList<Object>>();
	actual = Cache.getList("foo");
    }
//-------------------Driver.class Tests-------------------------------
}
