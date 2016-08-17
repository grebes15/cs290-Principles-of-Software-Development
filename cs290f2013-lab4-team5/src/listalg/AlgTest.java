/**
 * AlgTest.java
 *
 * Provides code for testng ListAlg.java.
 *
 * @author Matthew Hajduk
 * @author Brian Graham
 * @author Anton Kote
 * @author Andreas Landgerbe
 */

package listalg;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class AlgTest {

/*
Test for boolean inputs. Tests generateList 
in class ListAlg for 4 boolean inputs.
*/
	@Test
    public void testBoolean4() {
        String expected = new String("[[false, false, true, true], [true, false, false, true], [true, false, true, false], [false, true, false, true], [false, true, true, false], [false, false, true, true]]");
        ArrayList<Boolean> ArrayInput = new ArrayList<Boolean>();
        ArrayInput.add(false);
        ArrayInput.add(false);
        ArrayInput.add(true);
        ArrayInput.add(true);
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }
    
/*
Test for boolean inputs. Tests generateList 
in class ListAlg for 8 boolean inputs.
*/
    @Test
    public void testBoolean8() {
        String expected = new String("[[false, false, true, true, false, false, true, true], [true, false, false, true, false, false, true, true], [true, false, true, false, false, false, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, false, false, true, true], [true, false, true, true, false, false, false, true], [true, false, true, true, false, false, true, false], [false, true, false, true, false, false, true, true], [false, true, true, false, false, false, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, false, false, true, true], [false, true, true, true, false, false, false, true], [false, true, true, true, false, false, true, false], [false, false, true, true, false, false, true, true], [false, false, false, true, true, false, true, true], [false, false, false, true, false, true, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, false, false, true, true], [false, false, true, false, true, false, true, true], [false, false, true, false, false, true, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, false, false, true, true], [false, false, true, true, true, false, false, true], [false, false, true, true, true, false, true, false], [false, false, true, true, false, true, false, true], [false, false, true, true, false, true, true, false], [false, false, true, true, false, false, true, true]]");
        ArrayList<Boolean> ArrayInput = new ArrayList<Boolean>();
        ArrayInput.add(false);
        ArrayInput.add(false);
        ArrayInput.add(true);
        ArrayInput.add(true);
        ArrayInput.add(false);
        ArrayInput.add(false);
        ArrayInput.add(true);
        ArrayInput.add(true);
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }
    
/*
Test for String inputs. Tests generateList 
in class ListAlg for 4 String inputs.
*/
	@Test
    public void testString4() {
        String expected = new String("[[b, a, c, d], [c, b, a, d], [d, b, c, a], [a, c, b, d], [a, d, c, b], [a, b, d, c]]");
        ArrayList<String> ArrayInput = new ArrayList<String>();
        ArrayInput.add("a");
        ArrayInput.add("b");
        ArrayInput.add("c");
        ArrayInput.add("d");
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }
    
/*
Test for String inputs. Tests generateList 
in class ListAlg for 8 String inputs.
*/
    	@Test
    public void testString8() {
        String expected = new String("[[b, a, c, d, ab, bc, cd, de], [c, b, a, d, ab, bc, cd, de], [d, b, c, a, ab, bc, cd, de], [ab, b, c, d, a, bc, cd, de], [bc, b, c, d, ab, a, cd, de], [cd, b, c, d, ab, bc, a, de], [de, b, c, d, ab, bc, cd, a], [a, c, b, d, ab, bc, cd, de], [a, d, c, b, ab, bc, cd, de], [a, ab, c, d, b, bc, cd, de], [a, bc, c, d, ab, b, cd, de], [a, cd, c, d, ab, bc, b, de], [a, de, c, d, ab, bc, cd, b], [a, b, d, c, ab, bc, cd, de], [a, b, ab, d, c, bc, cd, de], [a, b, bc, d, ab, c, cd, de], [a, b, cd, d, ab, bc, c, de], [a, b, de, d, ab, bc, cd, c], [a, b, c, ab, d, bc, cd, de], [a, b, c, bc, ab, d, cd, de], [a, b, c, cd, ab, bc, d, de], [a, b, c, de, ab, bc, cd, d], [a, b, c, d, bc, ab, cd, de], [a, b, c, d, cd, bc, ab, de], [a, b, c, d, de, bc, cd, ab], [a, b, c, d, ab, cd, bc, de], [a, b, c, d, ab, de, cd, bc], [a, b, c, d, ab, bc, de, cd]]");
        ArrayList<String> ArrayInput = new ArrayList<String>();
        ArrayInput.add("a");
        ArrayInput.add("b");
        ArrayInput.add("c");
        ArrayInput.add("d");
        ArrayInput.add("ab");
        ArrayInput.add("bc");
        ArrayInput.add("cd");
        ArrayInput.add("de");
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }
    
    /* Test for double inputs. Tests generateList in class ListAlg for 4 double inputs. */
	@Test
	public void testDouble4() {
	String expected = new String("[[3.65, 2.57, 15.98, 100.53], [15.98, 3.65, 2.57, 100.53], [100.53, 3.65, 15.98, 2.57], [2.57, 15.98, 3.65, 100.53], [2.57, 100.53, 15.98, 3.65], [2.57, 3.65, 100.53, 15.98]]");
        ArrayList<Double> ArrayInput = new ArrayList<Double>();
        ArrayInput.add(2.57);
        ArrayInput.add(3.65);
        ArrayInput.add(15.98);
        ArrayInput.add(100.53);
        List commandLineTest = new ArrayList(ArrayInput);
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }

	/* Test for double inputs. Tests generateList in class ListAlg for 8 double inputs. */
	@Test
	public void testDouble8() {
	String expected = new String("[[6.79, 1.97, 27.08, 1000.43, 18.3, 90.7, 59.39, 1349.9], [27.08, 6.79, 1.97, 1000.43, 18.3, 90.7, 59.39, 1349.9], [1000.43, 6.79, 27.08, 1.97, 18.3, 90.7, 59.39, 1349.9], [18.3, 6.79, 27.08, 1000.43, 1.97, 90.7, 59.39, 1349.9], [90.7, 6.79, 27.08, 1000.43, 18.3, 1.97, 59.39, 1349.9], [59.39, 6.79, 27.08, 1000.43, 18.3, 90.7, 1.97, 1349.9], [1349.9, 6.79, 27.08, 1000.43, 18.3, 90.7, 59.39, 1.97], [1.97, 27.08, 6.79, 1000.43, 18.3, 90.7, 59.39, 1349.9], [1.97, 1000.43, 27.08, 6.79, 18.3, 90.7, 59.39, 1349.9], [1.97, 18.3, 27.08, 1000.43, 6.79, 90.7, 59.39, 1349.9], [1.97, 90.7, 27.08, 1000.43, 18.3, 6.79, 59.39, 1349.9], [1.97, 59.39, 27.08, 1000.43, 18.3, 90.7, 6.79, 1349.9], [1.97, 1349.9, 27.08, 1000.43, 18.3, 90.7, 59.39, 6.79], [1.97, 6.79, 1000.43, 27.08, 18.3, 90.7, 59.39, 1349.9], [1.97, 6.79, 18.3, 1000.43, 27.08, 90.7, 59.39, 1349.9], [1.97, 6.79, 90.7, 1000.43, 18.3, 27.08, 59.39, 1349.9], [1.97, 6.79, 59.39, 1000.43, 18.3, 90.7, 27.08, 1349.9], [1.97, 6.79, 1349.9, 1000.43, 18.3, 90.7, 59.39, 27.08], [1.97, 6.79, 27.08, 18.3, 1000.43, 90.7, 59.39, 1349.9], [1.97, 6.79, 27.08, 90.7, 18.3, 1000.43, 59.39, 1349.9], [1.97, 6.79, 27.08, 59.39, 18.3, 90.7, 1000.43, 1349.9], [1.97, 6.79, 27.08, 1349.9, 18.3, 90.7, 59.39, 1000.43], [1.97, 6.79, 27.08, 1000.43, 90.7, 18.3, 59.39, 1349.9], [1.97, 6.79, 27.08, 1000.43, 59.39, 90.7, 18.3, 1349.9], [1.97, 6.79, 27.08, 1000.43, 1349.9, 90.7, 59.39, 18.3], [1.97, 6.79, 27.08, 1000.43, 18.3, 59.39, 90.7, 1349.9], [1.97, 6.79, 27.08, 1000.43, 18.3, 1349.9, 59.39, 90.7], [1.97, 6.79, 27.08, 1000.43, 18.3, 90.7, 1349.9, 59.39]]");
        ArrayList<Double> ArrayInput = new ArrayList<Double>();
        ArrayInput.add(1.97);
        ArrayInput.add(6.79);
        ArrayInput.add(27.08);
        ArrayInput.add(1000.43);
		ArrayInput.add(18.30);
        ArrayInput.add(90.70);
        ArrayInput.add(59.39);
        ArrayInput.add(1349.90);
        List commandLineTest = new ArrayList(ArrayInput);
        ArrayList actual = ListAlg.generateList(ArrayInput);
        assertEquals(expected, actual.toString());
    }
    
    @Test
    public void intTestShort() {
        
        //String[] strTest = {"asdasdas","asd"};
        //ArrayList testInp = new ArrayList(ArrayUtils.toObject(intList));
        //ArrayList testString = new ArrayList(Arrays.asList(strTest));
        ArrayList testInp2 = new ArrayList();
        testInp2.add(1);
        testInp2.add(2);
        testInp2.add(3);
        testInp2.add(4);
        String expected = new String("[[2, 1, 3, 4], [3, 2, 1, 4], [4, 2, 3, 1], [1, 3, 2, 4], [1, 4, 3, 2], [1, 2, 4, 3]]");
        ArrayList actual =  ListAlg.generateList((ArrayList)testInp2);
        
        assertEquals(expected, actual.toString());
    }

    @Test
    public void intTestLong() {
        ArrayList testInp2 = new ArrayList();
        testInp2.add(1);
        testInp2.add(2);
        testInp2.add(3);
        testInp2.add(4);
        testInp2.add(5);
        testInp2.add(6);
        testInp2.add(7);
        testInp2.add(8);
        String expected = new String("[[2, 1, 3, 4, 5, 6, 7, 8], [3, 2, 1, 4, 5, 6, 7, 8], [4, 2, 3, 1, 5, 6, 7, 8], [5, 2, 3, 4, 1, 6, 7, 8], [6, 2, 3, 4, 5, 1, 7, 8], [7, 2, 3, 4, 5, 6, 1, 8], [8, 2, 3, 4, 5, 6, 7, 1], [1, 3, 2, 4, 5, 6, 7, 8], [1, 4, 3, 2, 5, 6, 7, 8], [1, 5, 3, 4, 2, 6, 7, 8], [1, 6, 3, 4, 5, 2, 7, 8], [1, 7, 3, 4, 5, 6, 2, 8], [1, 8, 3, 4, 5, 6, 7, 2], [1, 2, 4, 3, 5, 6, 7, 8], [1, 2, 5, 4, 3, 6, 7, 8], [1, 2, 6, 4, 5, 3, 7, 8], [1, 2, 7, 4, 5, 6, 3, 8], [1, 2, 8, 4, 5, 6, 7, 3], [1, 2, 3, 5, 4, 6, 7, 8], [1, 2, 3, 6, 5, 4, 7, 8], [1, 2, 3, 7, 5, 6, 4, 8], [1, 2, 3, 8, 5, 6, 7, 4], [1, 2, 3, 4, 6, 5, 7, 8], [1, 2, 3, 4, 7, 6, 5, 8], [1, 2, 3, 4, 8, 6, 7, 5], [1, 2, 3, 4, 5, 7, 6, 8], [1, 2, 3, 4, 5, 8, 7, 6], [1, 2, 3, 4, 5, 6, 8, 7]]");
        ArrayList actual =  ListAlg.generateList(testInp2);
        assertEquals(expected, actual.toString());
    }

    @Test
    public void motleyShort() {//Tests a short motley assortment of other types
        //String[] strTest = {"asdasdas","asd"};
        //ArrayList testInp = new ArrayList(ArrayUtils.toObject(intList));
        //ArrayList testString = new ArrayList(Arrays.asList(strTest));
        ArrayList testInp2 = new ArrayList();        
        ArrayList testInp3 = new ArrayList();
        testInp3.add("inString");
        testInp3.add(2);
        testInp2.add(true);
        testInp2.add(3.24);
        testInp2.add("String");
        testInp2.add(testInp3);

        String expected = new String("[[3.24, true, String, [inString, 2]], [String, 3.24, true, [inString, 2]], [[inString, 2], 3.24, String, true], [true, String, 3.24, [inString, 2]], [true, [inString, 2], String, 3.24], [true, 3.24, [inString, 2], String]]");
        ArrayList actual =  ListAlg.generateList((ArrayList)testInp2);
        //System.out.println(actual.toString());
        assertEquals(expected, actual.toString());
    }

    @Test
    public void motleyLong() {//Tests a long motley assortment of other types
        ArrayList testInp2 = new ArrayList();      
        ArrayList testInp3 = new ArrayList();
         ArrayList testInp4 = new ArrayList();
        testInp3.add("inString");
        testInp3.add(2);
        testInp2.add(true);
        testInp2.add(3.24);
        testInp2.add("String");
        testInp2.add(testInp3);

         testInp4.add("inString2");
        testInp4.add(3);
        testInp2.add(false);
        testInp2.add(6.24);
        testInp2.add("String2");
        testInp2.add(testInp4);

        String expected = new String("[[3.24, true, String, [inString, 2], false, 6.24, String2, [inString2, 3]], [String, 3.24, true, [inString, 2], false, 6.24, String2, [inString2, 3]], [[inString, 2], 3.24, String, true, false, 6.24, String2, [inString2, 3]], [false, 3.24, String, [inString, 2], true, 6.24, String2, [inString2, 3]], [6.24, 3.24, String, [inString, 2], false, true, String2, [inString2, 3]], [String2, 3.24, String, [inString, 2], false, 6.24, true, [inString2, 3]], [[inString2, 3], 3.24, String, [inString, 2], false, 6.24, String2, true], [true, String, 3.24, [inString, 2], false, 6.24, String2, [inString2, 3]], [true, [inString, 2], String, 3.24, false, 6.24, String2, [inString2, 3]], [true, false, String, [inString, 2], 3.24, 6.24, String2, [inString2, 3]], [true, 6.24, String, [inString, 2], false, 3.24, String2, [inString2, 3]], [true, String2, String, [inString, 2], false, 6.24, 3.24, [inString2, 3]], [true, [inString2, 3], String, [inString, 2], false, 6.24, String2, 3.24], [true, 3.24, [inString, 2], String, false, 6.24, String2, [inString2, 3]], [true, 3.24, false, [inString, 2], String, 6.24, String2, [inString2, 3]], [true, 3.24, 6.24, [inString, 2], false, String, String2, [inString2, 3]], [true, 3.24, String2, [inString, 2], false, 6.24, String, [inString2, 3]], [true, 3.24, [inString2, 3], [inString, 2], false, 6.24, String2, String], [true, 3.24, String, false, [inString, 2], 6.24, String2, [inString2, 3]], [true, 3.24, String, 6.24, false, [inString, 2], String2, [inString2, 3]], [true, 3.24, String, String2, false, 6.24, [inString, 2], [inString2, 3]], [true, 3.24, String, [inString2, 3], false, 6.24, String2, [inString, 2]], [true, 3.24, String, [inString, 2], 6.24, false, String2, [inString2, 3]], [true, 3.24, String, [inString, 2], String2, 6.24, false, [inString2, 3]], [true, 3.24, String, [inString, 2], [inString2, 3], 6.24, String2, false], [true, 3.24, String, [inString, 2], false, String2, 6.24, [inString2, 3]], [true, 3.24, String, [inString, 2], false, [inString2, 3], String2, 6.24], [true, 3.24, String, [inString, 2], false, 6.24, [inString2, 3], String2]]");
        ArrayList actual =  ListAlg.generateList(testInp2);
        //System.out.println(actual.toString());
        assertEquals(expected, actual.toString());
    }

    @Test
    public void ArrayListShort() {//Tests a short arraylist of arraylist
        //String[] strTest = {"asdasdas","asd"};
        //ArrayList testInp = new ArrayList(ArrayUtils.toObject(intList));
        //ArrayList testString = new ArrayList(Arrays.asList(strTest));
        ArrayList testInp = new ArrayList();       
        ArrayList testInp1 = new ArrayList();
        testInp1.add(1);
        testInp.add(testInp1);
      ArrayList testInp2 = new ArrayList();
      testInp2.add(2);
      testInp.add(testInp2);
      ArrayList testInp3 = new ArrayList();
      testInp3.add(3);
      testInp.add(testInp3);
      ArrayList testInp4 = new ArrayList();
      testInp4.add(4);
      testInp.add(testInp4);
        String expected = new String("[[[2], [1], [3], [4]], [[3], [2], [1], [4]], [[4], [2], [3], [1]], [[1], [3], [2], [4]], [[1], [4], [3], [2]], [[1], [2], [4], [3]]]");
        ArrayList actual =  ListAlg.generateList((ArrayList)testInp);
        //System.out.println(actual.toString());
        assertEquals(expected, actual.toString());
    }

    @Test
    public void ArraylistLong() {//Tests a long arraylist of arraylist
        ArrayList testInp = new ArrayList();       
        ArrayList testInp1 = new ArrayList();
        testInp1.add(1);
        testInp.add(testInp1);
      ArrayList testInp2 = new ArrayList();
      testInp2.add(2);
      testInp.add(testInp2);
      ArrayList testInp3 = new ArrayList();
      testInp3.add(3);
      testInp.add(testInp3);
      ArrayList testInp4 = new ArrayList();
      testInp4.add(4);
      testInp.add(testInp4);
      ArrayList testInp5 = new ArrayList();
        testInp5.add(5);
        testInp.add(testInp5);
      ArrayList testInp6 = new ArrayList();
      testInp6.add(6);
      testInp.add(testInp6);
      ArrayList testInp7 = new ArrayList();
      testInp7.add(7);
      testInp.add(testInp7);
      ArrayList testInp8 = new ArrayList();
      testInp8.add(8);
      testInp.add(testInp8);

        String expected = new String("[[[2], [1], [3], [4], [5], [6], [7], [8]], [[3], [2], [1], [4], [5], [6], [7], [8]], [[4], [2], [3], [1], [5], [6], [7], [8]], [[5], [2], [3], [4], [1], [6], [7], [8]], [[6], [2], [3], [4], [5], [1], [7], [8]], [[7], [2], [3], [4], [5], [6], [1], [8]], [[8], [2], [3], [4], [5], [6], [7], [1]], [[1], [3], [2], [4], [5], [6], [7], [8]], [[1], [4], [3], [2], [5], [6], [7], [8]], [[1], [5], [3], [4], [2], [6], [7], [8]], [[1], [6], [3], [4], [5], [2], [7], [8]], [[1], [7], [3], [4], [5], [6], [2], [8]], [[1], [8], [3], [4], [5], [6], [7], [2]], [[1], [2], [4], [3], [5], [6], [7], [8]], [[1], [2], [5], [4], [3], [6], [7], [8]], [[1], [2], [6], [4], [5], [3], [7], [8]], [[1], [2], [7], [4], [5], [6], [3], [8]], [[1], [2], [8], [4], [5], [6], [7], [3]], [[1], [2], [3], [5], [4], [6], [7], [8]], [[1], [2], [3], [6], [5], [4], [7], [8]], [[1], [2], [3], [7], [5], [6], [4], [8]], [[1], [2], [3], [8], [5], [6], [7], [4]], [[1], [2], [3], [4], [6], [5], [7], [8]], [[1], [2], [3], [4], [7], [6], [5], [8]], [[1], [2], [3], [4], [8], [6], [7], [5]], [[1], [2], [3], [4], [5], [7], [6], [8]], [[1], [2], [3], [4], [5], [8], [7], [6]], [[1], [2], [3], [4], [5], [6], [8], [7]]]");
        ArrayList actual =  ListAlg.generateList(testInp);
        //System.out.println(actual.toString());
        assertEquals(expected, actual.toString());
    }
    
    
    @Test
    public void testIO() {
    	// Intercept console output
    	ByteArrayOutputStream output = new ByteArrayOutputStream(100);
    	System.setOut(new PrintStream(output));

    	String[] args = {"g", "20", "q", "3"};

		ListAlgIO.main(args);
		assertEquals(output.toString(), "List generated from command line inputs: [[20, g, q, 3], [q, 20, g, 3], [3, 20, q, g], [g, q, 20, 3], [g, 3, q, 20], [g, 20, 3, q]]\n");
    }
    
    @Test
    public void testConstruct() {
		ListAlg l = new ListAlg();
        
        assertNotNull(l);
    }

	@Test
    public void testConstruct2() {
		AllTests a = new AllTests();
        
        assertNotNull(a);
    }
    
    @Test
    public void testConstruct3() {
		ListAlgIO ll = new ListAlgIO();
        
        assertNotNull(ll);
    }
}
