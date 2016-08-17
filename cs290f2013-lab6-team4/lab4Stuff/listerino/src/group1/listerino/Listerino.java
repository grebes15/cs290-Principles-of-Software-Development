//Initial Start of file, this will be our working class
package group1.listerino;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Listerino is the working method in this program, it essentially is the control flow and does all
 *  the main operations needed.
 * @author  Adam, the god who's code compiles on the first time, Wechter esq. du Awesomeness
 */
public class Listerino{
    /**
     * sets table must be called everytime to start
     *
     */
    public static void start(){
        Cache.setTable();
    }


    /**
     *  Pertains to getting our new list of permutations including getting the new list of objects
     *  as well as it gets a comment.
     * @param initial  Initial list of objects can be null in which case its being called by the ui
     * @param comment  if null also called by ui and will be determined by user
     * @return
     */
    public static ArrayList<ArrayList<Object>> listIn(ArrayList<Object> initial, String comment){
        ArrayList<ArrayList<Object>> perms = new ArrayList<ArrayList<Object>>();
        if(comment.equals(null))
            comment = "";
        String disc = writeDescription(initial);
        //check here if already done (Memoization)
        int id = Cache.lookUp(disc);
        if(id == 0){  //if there was no existing set of perms in db
            perms = permutate(initial);
            Cache.saveList(disc, perms);  //saves the actual permutations
            Cache.setNewEntry(disc, comment);  //Puts entry into the database for retrieval later
        }
        else{        //if there was an existing set of perms, pull it an dput it in perms
            String location = Cache.getLocation(id);
            perms = Cache.getList(location);
        }
        return perms;
    }

    /**
     *  basically takes the list and puts it in a string form that can be used for the file name and 
     *  searching in the db
     * @param list which is the list that will be changed into string form for unique filename
     * @return The string description of the list
     */
    public static String writeDescription(ArrayList<Object> list){
        List<String> strings = new ArrayList<String>();
        for(Object object : list){
            strings.add(object != null ? object.toString() : null);  //prints object toString, unless null then prints null
        }
        StringBuilder sb = new StringBuilder(String.valueOf(""));
        for(String s : strings)
            sb.append(s);

        String disc = sb.toString();           
        return disc;
    }

    /**
     *  Allows the user to add elements to the list which will be permutated
     *  Can be forgone though maybe probably.....
     * @param list the current list of things to permutate
     * @param e an object element you wish to add
     * @return The current state of the list of things to permutate
     */
    public static ArrayList<Object> addElement(ArrayList<Object> list, Object e){
        list.add(e);
        return list;
    }

    /**
     *  The look at history method, shows the current state of data base of all previous permutations
     *  Then the user has the oppertunity to select one for use in something else.  So if they want one it'll
     *  return that list for the usre to use, otherwise it'll return null should be pulled in conjuction with the list display
     * @return Returns the string of history
     */
    public static String checkHistory(){
        String history =  Cache.getHistory();
        return history;
    }

    /**
     *  Makes all possible swaps from the initial list of objects and puts them in the arraylist of arraylists
     * @param initial The initial set of objects
     * @return The arrayList of arrayLIst, basically all the possible perms that he wants.
     */
    public static ArrayList<ArrayList<Object>> permutate(ArrayList<Object> initial){
        ArrayList<ArrayList<Object>> permed = new ArrayList<ArrayList<Object>>();
        permed.add(initial);  //original is 0
        for(int i = 0; i<initial.size()-1; i++){
            for(int j = i+1; j < initial.size(); j++){
                ArrayList<Object> tempo = new ArrayList<Object>();
                tempo.addAll(initial);
                Object temp = tempo.get(j);
                tempo.set(j, tempo.get(i));
                tempo.set(i, temp);
                System.out.println(viewListObj(tempo));
                permed.add(tempo);
            }
        }
        return permed;
    }

    /**
     *   Pulls a specific list from the set of permutations
     *
     * @param list  list of lists
     * @param index  which list you want
     * @return   list of objects that you want
     */
    public static ArrayList<Object> listInteract(ArrayList<ArrayList<Object>> list, int index){
            ArrayList<Object> sele = list.get(index); //display will have the inital as the 0th
            return sele;
    }

    /**
     *  This is the way that we will nicely display the list of permutations or a specific list that he pulls in string form
     *  It relies on either one or the other lists being null, this will determine which test it will run.
     * @param listA  The array list of array lists (all possible permutations)
     */
    public static String viewListArr(ArrayList<ArrayList<Object>> listA){
        StringBuilder sb = new StringBuilder(String.valueOf(""));
        String out;
        int i= 0;
        if(listA.isEmpty())
            return out="No list given";

        for(ArrayList<Object> alistO : listA){
            List<String> strings = new ArrayList<String>();
            sb.append(i);
            i++;
            sb.append("\t");
            for(Object o: alistO){
                strings.add(o != null ? o.toString() : null);  //prints object toString, unless null then prints null
            }
            for(String s : strings){
                sb.append(s);
                sb.append(", ");
            }
            sb.append("\n");
        }
        out = sb.toString();
        return out;
    }

    /**
     *  Views and returns a viewable nice pretty string of the objects in the list
     *
     * @param listO  list of objects
     * @return  string that looks nice
     */
    public static String viewListObj(ArrayList<Object> listO){

        StringBuilder sb = new StringBuilder(String.valueOf(""));
        String out;
        int i= 0;
        if(listO.isEmpty())
            return out = "No list given";

        List<String> strings = new ArrayList<String>();
        sb.append(i);
        i++;

        sb.append("\t");
        for(Object object : listO){
            strings.add(object != null ? object.toString() : null);  //prints object toString, unless null then prints null
        }
        for(String s : strings){
            sb.append(s);
            sb.append(", ");
        }
        out = sb.toString();
        return out;
    }

}

        

