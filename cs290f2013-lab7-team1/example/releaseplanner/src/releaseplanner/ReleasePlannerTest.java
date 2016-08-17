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
import org.hamcrest.Matcher;
import org.junit.Before;

public class ReleasePlannerTest 
{
    private static final double DELTA = 1e-15;
    private ReleasePlanner rp;   
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    @Before
    public void setUp()
    {
        int fixedCost = 10;
        Requirement r1 = new Requirement(1,5,10,"0");
        Requirement r2 = new Requirement(2,4,40,"1");
        Requirement r3 = new Requirement(3,6,30,"0");
        Requirement r4 = new Requirement(4,3,50,"1");
        ArrayList<Requirement> requirements = new ArrayList<Requirement>();
        requirements.add(r1);
        requirements.add(r2);
        requirements.add(r3);
        requirements.add(r4);
        rp = new ReleasePlanner(fixedCost, requirements);
        rp.optimizer();
    }

    @Test
    public void ReleasePlannerTest()
    {   
        int expectedFixedCost = 10;
        int expectedRequirementsSize  = 4;
        assertEquals(expectedFixedCost, rp.getFixedCost());
        assertEquals(expectedRequirementsSize, rp.getRequirements().length-1);
    }

    @Test
    public void testOptimizerKeepsRightValues()
    {
        Requirement[] optimizedRequirements = rp.getRequirements();
        assertTrue("Requirement 2 should be in includes",optimizedRequirements[2].getKeep());
        assertTrue("Requirement 4 should be in includes",optimizedRequirements[4].getKeep());
    }

    @Test
    public void testOptimizerDiscardsRightValues()
    {
        Requirement[] optimizedRequirements = rp.getRequirements();
        assertFalse("Requirement 1 should not be in includes",optimizedRequirements[1].getKeep());
        assertFalse("Requirement 3 should not be in includes",optimizedRequirements[3].getKeep());    
    }

    @Test
    public void OutputTest()
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream(100);  
        System.setOut(new PrintStream(output));

        rp.output();
        assertTrue(output.toString().contains("Total Cost:"));    
    }

    @Test
    public void testTotalBenefit()
    {   
        int expectedBeforeSetting = 0;
        int actualBeforeSetting = rp.getTotalBenefit();     
        assertEquals(expectedBeforeSetting,actualBeforeSetting);
        rp.setOutputVariables();
        int expectedAfterSetting = 90;
        int actualAfterSetting = rp.getTotalBenefit();
        assertEquals(expectedAfterSetting,actualAfterSetting);
    }

    @Test
    public void testTotalCost()
    {   
        int expectedBeforeSetting = 0;
        int actualBeforeSetting = rp.getTotalCost();     
        assertEquals(expectedBeforeSetting,actualBeforeSetting);
        rp.setOutputVariables();
        int expectedAfterSetting = 7;
        int actualAfterSetting = rp.getTotalCost();
        assertEquals(expectedAfterSetting,actualAfterSetting);
    }   

    @Test
    public void testProfit()
    {   
        int expectedBeforeSetting = 0;
        int actualBeforeSetting = rp.getProfit();     
        assertEquals(expectedBeforeSetting,actualBeforeSetting);
        rp.setOutputVariables();
        int expectedAfterSetting = 83;
        int actualAfterSetting = rp.getProfit();
        assertEquals(expectedAfterSetting,actualAfterSetting);
    }


    @Test
    public void testremainingBudget()
    {   
        int expectedBeforeSetting = 0;
        int actualBeforeSetting = rp.getRemainingBudget();     
        assertEquals(expectedBeforeSetting,actualBeforeSetting);
        rp.setOutputVariables();
        int expectedAfterSetting = 3;
        int actualAfterSetting = rp.getRemainingBudget();
        assertEquals(expectedAfterSetting,actualAfterSetting);
    }

}
