package edu.allegheny.kinetic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Error Tests, tested by the coverage tests to make sure all areas are covered.  Names are all descriptive as to what each method Tests
 *
 * @author  Prof. Kapfhammer, Tristan Challener, Adam Wechter
 */
public class KineticTest {

    /**
     * Tests to make sure that no zero values are accepted for mass
     *
     */
    @Test
    public void testVelocityComputationIsUndefined() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(5, 0);
        assertEquals(expected, actual);
    }

    /**
     *  If velocity is zero, it should return a zero
     *
     */
    @Test
    public void testVelocityComputationIsZero() {
        String expected = new String("0");
        String actual = Kinetic.computeVelocity(0, 5);
        assertEquals(expected, actual);
    }

    /**
     * Tests to make sure equation actually preforms calculation correctly
     * and the validness of the actual computational arethmitic.
     */
    @Test
    public void testVelocityComputationWithNonZeroValues() {
        String expected = new String("4");
        String actual = Kinetic.computeVelocity(8, 1);
        assertEquals(expected, actual);
    }

    /**
     * Makes sure that negative values return undefined since we can't use
     * negative values in this equation
     */
    @Test
    public void testVelocityComputationWithNegativeMass() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(8, -1);
        assertEquals(expected, actual);
    }

    /**
     * Makes sure that negative values return undefined since we cant use
     * negative values in this equation
     */
    @Test
    public void testVelocityComputationWithNegativeKinetic() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(-8, 1);
        assertEquals(expected, actual);
    }

    /**
     * Tests that if both are negative, it will return the toString as 
     * undefined
     */
    @Test
    public void testVelocityComputationWithNegativeMassKinetic() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(-8, -1);
        assertEquals(expected, actual);
    }

    /**
     * Tests default constructor that is not actually there
     * serves to get 100% coverage for Kinetic
     */
    @Test
    public void testKineticConstructor() {
        Kinetic kinetic = new Kinetic();
        assertNotNull(kinetic);
    }

    /**
     * Tests default constructor of AllTests that is not actually there
     * serves to get 100% coverage for AllTests
     */
    @Test
    public void testAllTestsConstructor() {
        AllTests alltests = new AllTests();
        assertNotNull(alltests);
    }

    /* Driver tests */
    /**
     * Tests default constructor of Driver that is not actually there
     * serves to get 100% coverage for Driver
     */
    @Test
    public void testDriverConstructor(){
        Driver driver = new Driver();
        assertNotNull(driver);
    }

    /**
     * Tests main mehtod to make sure that the integers parse correctly and no exceptions or errors happen
     * This test creates a lack of coverage because there is an unreached point that would only occur
     * if the test were to fail.  This is included for completeness though the test should always pass.
     */
    @Test
    public void testDriverMain(){
        String[] test = {"8", "1"};
        try
        {
            Driver.main(test);
        }
        catch(Exception e)
        {
            assertNull(e);
        }
        assertTrue(true);
    }

    /**
     * Tests the main method to make sure that our inputs are caught in the main method and errors are not
     * thrown back here (handled).  This will create a lack of coverage however this is intended and acceptable
     */
    @Test
    public void testDriverBadInput(){
        String[] test = {"Bad", "Input"};
        try
        {
            Driver.main(test);
        }
        catch(Exception e)
        {
            assertNull(e);
        }
        assertTrue(true);
    }
}
