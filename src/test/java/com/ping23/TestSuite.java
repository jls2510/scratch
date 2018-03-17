package com.ping23;

import org.junit.runners.Suite;

import com.ping23.util.TestFileUtilities;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestSuperStack.class, TestBigDecimalStringSort.class,
    TestFileUtilities.class })
public class TestSuite {
    // nothing
}
