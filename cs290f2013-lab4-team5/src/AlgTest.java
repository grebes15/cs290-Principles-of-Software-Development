/**
 * KineticTest.java
 *
 * Provides code for testng Kinetic.java.
 *
 * @author Nathaniel Blake
 * @author Matthew Hajduk
 * @author Mackenzie Jordan
 * @author Eric Weyant
 */

package ListAlg;
import java.io.ByteArrayOutputStream;

import java.io.PrintStream;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlgTest {

	/*@Test
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
    	String actual = Kinetic.computeVelocity(1, -1);
    	assertEquals(expected, actual);
	}

    @Test
    public void testVelocityComputationWithNegativeKinetic() {
    	// We expect 0 here, actually. 
    	// We have sqrt(n) with n < 0, which returns NaN,
    	// then, strangely, casting gives (int) NaN == 0.
    	String expected = new String("0");
    	String actual = Kinetic.computeVelocity(-1, 1);
    	assertEquals(expected, actual);
	}

    @Test
    public void testVelocityComputationWithKineticTripleMass() {
    	// This is a test that teases out the original error in the code,
    	// since when k/m = 3 (i.e., k = 3m), sqrt(3k/m) = sqrt(9) = 3,
    	// when it should be sqrt(2k/m) = sqrt(6) = 2.
    	String expected = new String("2");
    	String actual = Kinetic.computeVelocity(12, 4);
    	assertEquals(expected, actual);
	}

    @Test
    public void testMain() {
    	// Intercept console output
    	ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	String[] args = {"-k", "20", "-m", "3"};

		Kinetic.main(args);
		assertEquals(output.toString(), "The velocity is 3.\n");
    }

    @Test
    public void testNumber() {
    	// Intercept console output
    	ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	String[] args = {"-k", "-m", "3"};

		Kinetic.main(args);
		assertEquals(output.toString(), "Incorrect arguments: [-k, -m, 3].\nExample usage: java Kinetic -k 20 -m 5\n");
    }

	@Test
    public void testInvalid1() {
    	// Set up the input and output streams
    	ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	String[] args = {"-m", "10", "twenty", "kay"};

		Kinetic.main(args);
		assertEquals(output.toString(), "Invalid parameter: twenty.\nExample usage: java Kinetic -k 30 -m 10\n");
    }

	@Test
    public void testInvalid2() {
    	// Set up the input and output streams
    	ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	String[] args = {"-m", "-k", "7", "7"};

		Kinetic.main(args);
		assertEquals(output.toString(), "Invalid value for -m: -k\n");
    }

	@Test
    public void testConstruct() {
		Kinetic k = new Kinetic();
        
        assertNotNull(k);
    }

	@Test
    public void testConstruct2() {
		AllTests a = new AllTests();
        
        assertNotNull(a);
    }
*/
}
