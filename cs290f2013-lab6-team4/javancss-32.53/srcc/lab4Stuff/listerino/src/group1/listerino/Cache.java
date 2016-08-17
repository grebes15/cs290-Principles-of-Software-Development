//Java Cache Class
//
package group1.listerino;
import java.io.*;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 *  Cache, sets up file saving system for the history as well as a data base for history checking
 *  as well as memoization, cause we need memoization for it to run fast and have no redundancy.
 *  Also handles file storage of the histories and pulling from that back into an ArrayList form
 * @author Adam, Oh capitan my captain, Wechter Supreme Dude of the /0 degree
 */
public class Cache{
    static Connection c = null;
    static Statement stmt = null;

    /**
     *  Creation of the database of course, to be run once a time that it starts up, may try to make it only 
     *  Run once ever on any particular computer or if the database doesnt exist.
     */
    public static void setTable(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/../../Cache/test.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE LIST_STORAGE(" +
                        "ID INTEGER PRIMARY KEY," +
                        "FILE_LOC VARCHAR(1000) NOT NULL," +
                        "LIST_DISC VARCHAR(1000) NOT NULL," +
                        "COMMENT VARCHAR(500))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }catch(Exception e){ System.out.println(e.getClass().getName() + ": " + e.getMessage() );
                            System.exit(0);
        }
    }

    /**
     *  Runs a query to see if a description has been done already, i.e. has someone already run the current
     *  list before, if so we dont need to run it again so pull
     * @param initial The descripion of the file is unique per set of objects
     * @return Returns the id of the one we want or the one matching the description
     */
    public static int lookUp(String initial){
        int id = 0;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/../../Cache/test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String checkSQL = "SELECT ID from  LIST_STORAGE where LIST_DISC = " + initial + ";";
            ResultSet rs = stmt.executeQuery(checkSQL);
            id = rs.getInt("ID");  //gets the id if it exists would normally need while loop to loop through query but only one possible return
            rs.clos();
            stmt.close();
            c.close();
        }catch(Exception ignore){}
        return id;
    }
    
    /**
     *  Gets the location of the saved file of the permutations from the cache directory.  
     * @param id Id of the Permutation list that we want
     * @return Returns the file's location for it to be transformed and used
     */
    public static String getLocation(int id){
        String loc = "";
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/../../Cache/test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String checkSQL = "SELECT FILE_LOC from LIST_STORAGE where ID = "+id+";";
            ResultSet rs = stmt.executeQuery(checkSQL);
            loc += rs.getString("FILE_LOC");
            stmt.close();
            c.close();
        }catch(Exception ignore){}
        return loc;
    }

    /**
     *  Adds a new entry to the database of a unique list of permutations.  This will set comments file location
     *  and everything else the data base needs including id, and list descrpition
     * @param disc unique list description used for checking if list was inputted, and also unique filename
     * @param comment User made comment on list of permutations
     * @return Returns true if it worked successfully, false if it didnt.
     */
    public static boolean setNewEntry(String disc, String comment){
        boolean worked = false;
        String fileLoc = "/../../Cache/ListStorage/"+disc+".ser";

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/../../Cache/test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO LIST_STORAGE (ID, FILE_LOC, LIST_DISC, COMMENT) " +
                            "VALUES(NULL, '"+fileLoc+"', '"+disc+"', '"+comment+"');";
           stmt.executeUpdate(sql);  //preforms insert
           stmt.close();
           c.commit();
           c.close();
           worked = true;  //if it works set to true so that we know it works
        }catch(Exception ignore){}
        return worked;
    }

    /**
     *  Returns a formatted version of the history of all lists of permutations so that the user my use and reference
     * @return returns the history in the form of a string with one entry per line (id, file location, list disc and comment)
     */
    public static String getHistory(){
        StringBuilder sb = new StringBuilder(String.valueOf(""));
        String history = "";
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/../../Cache/test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String checkSQL = "SELECT * from LIST_STORAGE;";
            ResultSet rs = stmt.executeQuery(checkSQL);
            while(rs.next()){
                int id = rs.getInt("ID");
                String fileloc = rs.getString("FILE_LOC");
                String disc = rs.getString("LIST_DISC");
                String comment = rs.getString("COMMENT");
                sb.append(id);
                sb.append("\t");
                sb.append(fileloc);
                sb.append("\t");
                sb.append(disc);
                sb.append("\t");
                sb.append(comment);
                sb.append("\n");
            }
            stmt.close();
            c.close();
        }catch(Exception e){System.out.println("Problem grabbing history");}
        history = sb.toString();
        return history;
    }
    
        
    /**
     *  Saves the list to a file with a unique name based off of filename/list_disc.  uses objectoutputstream
     *  to preseve the integrity of the object by serializing the object.
     * @param fileName File name is the unique list pre permutation
     * @param list The ArrayList of ArrayList<objects> that we want to save into the file
     */
    public static void saveList(String fileName, ArrayList<ArrayList<Object>> list){
        try(
            OutputStream file = new FileOutputStream("/../../Cache/lists/"+fileName+".ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(list);
        }catch(IOException ex){
            System.out.println("Problem writing object");
        }
    }

    /**
     *  Pulls the list of permutations from a specific file for the user to reuse
     *  Its amazing cause i wrote it, and it uses objectinputstream to de-serialize
     * @param fileName Filename of the list of perms to pull
     * @return The arraylist of arraylist<object> from teh file
     */
    public static ArrayList<ArrayList<Object>> getList(String fileName){
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        try(
            InputStream file = new FileInputStream("/../../Cache/lists/"+fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
        ){
            list = (ArrayList<ArrayList<Object>>)input.readObject();
        }catch(IOException e){
            System.out.println("Problem reading file");
        }
        catch(ClassNotFoundException ex){
            System.out.println("classnotfound");
        }
        return list;
    }
    /**
     *
     *  Pulls in a file with a list of things that the user wishes to permutate.  Only used for ui although could be used if
     *  they want to serialize their own file
     * @param fileName  file of an initial list of objects .ser file
     * @return   the array list
     */
    public static ArrayList<Object> getListObj(String fileName){
        ArrayList<Object> list = new ArrayList<Object>();  
        try(
            InputStream file = new FileInputStream("/../../Cache/lists/"+fileName);        
            InputStream buffer = new BufferedInputStream(file);             
            ObjectInput input = new ObjectInputStream(buffer);              
        ){  
            list = (ArrayList<Object>)input.readObject();        
        }catch(IOException e){
            System.out.println("Problem reading file");                     
        }
        catch(ClassNotFoundException ex){System.out.println("classnotfound");}
        return list;                                                        
    }


}


    








