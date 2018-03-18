package com.ping23.hr_exercises;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ping23.hr_exercises.FindOdds;

public class FindOddsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindOdds() {
		
		int leftBound = 0;
		int rightBound = 10;
		int[] actuals = FindOdds.findOdds(leftBound, rightBound);
		
		int[] expecteds = {1,3,5,7,9};
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

}
