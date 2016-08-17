package releaseplanner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    // DataGeneratorTest 
	ReleasePlannerTest.class,
	RequirementTest.class,
	JCTest.class,
	ReleaseUITest.class,
})

public class AllTests {
}
