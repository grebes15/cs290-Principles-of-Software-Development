package releaseplanner;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;

public class RequirementTest {
    private static final double DELTA = 1e-15;

	// Tests Requirements constructer and getBenefit()
	@Test
	public void getBenefitTest()
    {
        Requirement req = new Requirement(1,10, 2, "name");
        int actual = req.getBenefit();
        assertEquals(2, actual);
    }
    // Tests Requirement constructor and getCost()
    @Test
    public void getCostTest()
    {
        Requirement req = new Requirement(1,10, 2, "name");
        int actual = req.getCost();
        assertEquals(10, actual);
    }
    // Tests Requirement constructor and toString()
	@Test
	public void toStringTest()
	{
		Requirement req = new Requirement(1, 3, 2, "testreq");
		String actual = req.toString();
		String expected = "1\t3\t2\ttestreq\n";
		assertEquals(expected, actual);	
	}
	// Tests getKeep() and setKeep() methods
	@Test
	public void keepTest()
    {
        Requirement req = new Requirement(1, 3, 2, "testreq");
        req.setKeep(true);
        boolean actual = req.getKeep();
        assertEquals(true, actual);
    }
}
