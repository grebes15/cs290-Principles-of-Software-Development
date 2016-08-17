package edu.allegheny.twitteranalysis.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	DBTest.class,
	AnalyticsTest.class
})

public class AllTests {
}
