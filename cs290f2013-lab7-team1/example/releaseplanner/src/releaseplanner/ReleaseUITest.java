package releaseplanner;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import com.beust.jcommander.*;

public class ReleaseUITest {
    // Test main()
    // Tests if main recieves a csv file correctly
   /**
    @Test
    public void mainCSVTest() throws FileNotFoundException, IOException
    {
            String[] argsv = {"-c", "20", "-file", "csvfile.csv"};
            ReleaseUI.main(argsv);
            JC jct = new JC();   
            new JCommander(jct, argsv);
            String req = jct.getFile();
            assertEquals("csvfile.csv", req);
            
            
    }**/
    // Tests if main recieves command line prompt correctly
    @Test
    public void mainCommanLinetest() throws FileNotFoundException, IOException
    {
            String[] argsv = {"-rq","1","580","874","1","2","1616","620","0"};
            ReleaseUI.main(argsv);
            JC jct = new JC();   
            new JCommander(jct, argsv);
            List<String> actual = jct.getRequirementList();
            List<String> expected = new ArrayList<String>(); 
            expected.add("1");
            expected.add("580");
            expected.add("874");
            expected.add("1");
            expected.add("2");
            expected.add("1616");
            expected.add("620");
            expected.add("0");
            assertEquals(expected, actual);
    }

    /* Tests the csvRead() method
    @Test
    public void csvReadTest()
    {
        ArrayList<Requirement> outputList = new ArrayList<Requirement>();
        outputList=ReleaseUI.csvRead("csvfile.csv");
        ArrayList<Requirement> expectedOutputList = new ArrayList<Requirement>();
        expectedOutputList.add(new Requirement(1,10,5,"?"));
        expectedOutputList.add(new Requirement(2,40,4,"Say"));
        expectedOutputList.add(new Requirement(3,30,6,"No"));
        expectedOutputList.add(new Requirement(4,50,3,"What"));
        assertEquals(expectedOutputList.toString(),outputList.toString());
    }

 */   
}
