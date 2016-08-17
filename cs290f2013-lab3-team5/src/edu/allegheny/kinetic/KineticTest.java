package edu.allegheny.kinetic;

import org.junit.Test;

import static org.junit.Assert.*;

public class KineticTest {

	@Test
    public void testVelocityComputationIsUndefined() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(5, 0);
        assertEquals(expected, actual);
    }

	@Test
    public void testVelocityComputationIsZero() {
        String expected = new String("0");
        String actual = Kinetic.computeVelocity(0, 5);
        assertEquals(expected, actual);
    }

	@Test
    public void testVelocityComputationWithNonZeroValues() {
        String expected = new String("4");
        String actual = Kinetic.computeVelocity(8, 1);
        assertEquals(expected, actual);
    }

	@Test
    public void testVelocityComputationWithNegativeMass() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(8, -2);
        assertEquals(expected, actual);
    }

	@Test
    public void testVelocityComputationWithNegativeKinetic() {
        String expected = new String("Undefined");
        String actual = Kinetic.computeVelocity(-4, 1);
        assertEquals(expected, actual);

    }

	@Test
    public void testVelocityComputationWithCharacters() {
        String expected = new String("0");
        String actual = Kinetic.computeVelocity('a','b');
        assertEquals(expected, actual);
    }
	@Test
    public void testVelocityComputationWithLargeNumbers() {
        String expected = new String("2");
        String actual = Kinetic.computeVelocity(500, 200);
        assertEquals(expected, actual);
    
    }

}
