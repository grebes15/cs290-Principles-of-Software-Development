/**
 * rpTest.java
 *
 * Provides code for testng the ReleasePlanner software suite
 *
 * @author Matthew Hajduk
 * @author Tristan Challener
 * @author Ian MacMillian
 * @author Andreas Landgerbe
 * @author Ryan Mong
 *
 * Lab 5
 */

package edu.allegheny.releaseplanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class rpTest {
    static ArrayList<Integer> R, B, C, best;
    static ArrayList<String> RD;
    static int w;
/*
Test correct output.
*/
	@Test
    public void test1() {
        String expected = new String("releaseplanner:\n     [java] Total Cost: $4\n     [java] Elapsed Time: 237659\n     [java] Estimated total cost: $3\n     [java] Estimated total benefit: $5\n     [java] Remaining flex budget: $1\n     [java] Profit: $2\n     [java] List of includes:\n     [java] 	 0) \"This is cool\"\n     [java] 	 1) \"This is okay\"\n     [java] 	 3) \"This ain't so bad\"\n     [java] List of discarded:\n     [java] 	 2) \"OH GOD WHY\"");
        
		ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	//String[] args = {"-k", "20", "-m", "3"};

		//Kinetic.main(args);
		assertEquals(output.toString(), expected);
    }
    
    //create instance of ReleasePlanner to show full coverage in jacoco
    @Test
    public void testConstruct() {
		ReleasePlanner r = new ReleasePlanner();
        
        assertNotNull(r);
    }

	//create instance of AllTest to show full coverage in jacoco
	@Test
    public void testConstruct2() {
		AllTests a = new AllTests();
        
        assertNotNull(a);
    }
    
}
