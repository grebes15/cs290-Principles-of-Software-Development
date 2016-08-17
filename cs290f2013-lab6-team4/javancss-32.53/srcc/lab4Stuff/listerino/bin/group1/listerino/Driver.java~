package group1.listerino;
import java.util.ArrayList;
import java.util.Scanner;
public class Driver{
    public static void main(String[] args){
        String flag = args[0];
        Scanner scan = new Scanner(System.in);
        if(flag.equals("-n")){  //go through a console ui
            Listerino.start();
            System.out.println("Welcome to the list swapper.");
            System.out.println("this program will swap objects from the intial to get all possible permutations from the initial");
            System.out.println("Menu Select Options:  ");
            System.out.println("  [1]- History");
            System.out.println("  [2]- New List");
            System.out.print("\nWhat do you wish to do (1/2)?   ");
            int decision = scan.nextInt();
            while(decision != 1 && decision != 2){
                System.out.print("Inproper Entry try again (1 or 2):  ");
            }
            if(decision == 1){
                goHistory();
            }
            else{
                newListIn();
            }
            System.exit(0);
        }
        else if(flag.equals("-c")){ //command line input read in ser
            Listerino.start();
            String fp = args[1];
            String comment = args[2];
            ArrayList<Object> list = Cache.getListObj(fp);
            ArrayList<ArrayList<Object>> perms = Listerino.listIn(list, comment);
            System.out.println("PermList generated");
            System.out.println("Perm list cached");
            System.out.println("-------- List of permutations ---------");
            System.out.println(Listerino.viewListArr(perms));
            System.out.println("\n\nThank you for using our program");
            System.exit(0);
        }
        else{  //wrong flags tel em wat to do 
            System.out.println("Program usage:");
            System.out.println("java Driver -n:  will take you into console ui");
            System.out.println("java Driver -c [filepath and file name of .ser file DO NOT INCLUDE .ser] \"[comment]\"");
            System.out.println("Exitting program now please try again");
            System.exit(0);
        }
    }

    /**
     *  only need for ui, adds a new list
     */
    public static void newListIn(){
        ArrayList<Object> list = addToList();
        String comment = addComment();
        ArrayList<ArrayList<Object>> perms = Listerino.listIn(list, comment);
        System.out.println("PermList generated");
        System.out.println("Perm list cached");
        System.out.println("-------- List of permutations ---------");
        System.out.println(Listerino.viewListArr(perms));
        System.out.println("\n\nThank you for using our program");
        System.exit(0);
    }


    /**
     *  Once again ui purposes only for history and stuff
     *
     */
    public static void goHistory(){
        Scanner scan = new Scanner(System.in);
        System.out.println("------------------ History ----------------");
        System.out.println(Listerino.checkHistory());
        System.out.print("\n\n");
        System.out.print("Do you wish to view a specific list of perms(y/n)?  ");
        String c = scan.nextLine();
        while(!c.equals("y") && !c.equals("n")){
            System.out.print("\n Incorrect entry enter y or n:  ");
            c = scan.nextLine();
        }
        if(c.equals("y")){
            System.out.print("What is the id that you wish to see?(id#)  ");
            int id = scan.nextInt();
            String location = Cache.getLocation(id);
            ArrayList<ArrayList<Object>> list = Cache.getList(location);
            System.out.println(Listerino.viewListArr(list));
            System.out.println("Thank you for using our program!");
            scan.close();
            System.exit(0);
        }
        else{
            scan.close();
            System.out.println("Thank you for using our program!");
            System.exit(0);
        }
    }

    /**
     *  Only for adding to a new list from the ui, not necessary for api
     *
     * @return  the new initial list to permutate
     */
    public static ArrayList<Object> addToList(){
        ArrayList<Object> list = new ArrayList<Object>();
        Scanner scan = new Scanner(System.in);
        System.out.println("No list given so must be made");
        System.out.println("Enter number of elements you wish to add");
        int elements = scan.nextInt();
        for(int i = 0; i < elements; i++)
            list = Listerino.addElement(list,(Object)scan.nextLine());
        scan.close();
        return list;
    }

    /**
     *  Allows for the addition of a comment by the ui
     * @return  a comment
     */
    public static String addComment(){
        Scanner scan = new Scanner(System.in);
        System.out.println("No comment given so must be made");
        System.out.println("Input comment here");
        String comment = scan.nextLine();
        scan.close();
        return comment;
    }

}
