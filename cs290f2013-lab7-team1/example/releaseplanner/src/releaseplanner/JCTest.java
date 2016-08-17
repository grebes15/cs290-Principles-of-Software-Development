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
import com.beust.jcommander.*;

public class JCTest {
    // Tests the getCost() method 
    @Test
    public void getCostTest()
    {
        JC jct = new JC();
        String[] argv = {"-c","10"};
        new JCommander(jct, argv);
        int actual = jct.getCost();
        assertEquals(10, actual);
    }
    // Tests the getRequirementsList() method
    @Test
    public void getRequirementsListTest()
    {
        JC jct = new JC();
        String[] argv = {"-rq","1","580","874"};
        new JCommander(jct, argv);
        List<String> actual = jct.getRequirementList();
        List<String> expected = new ArrayList<String>(); 
        expected.add("1");
        expected.add("580");
        expected.add("874");
        assertEquals(expected, actual);
    }
    // Tests the getFile() method
    @Test
    public void getFileTest()
    {
        JC jct = new JC();
        String[] argv = {"-file","csvfile.csv"};
        new JCommander(jct, argv);
        String actual = jct.getFile();
        assertEquals("csvfile.csv", actual);

    }
}
