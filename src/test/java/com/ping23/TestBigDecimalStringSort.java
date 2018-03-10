package test.java.com.ping23;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.ping23.BigDecimalStringSort;

public class TestBigDecimalStringSort {

    @Test
    public void testGetSortedStringItems() {
        // fail("Not yet implemented");

        final String TEST_INPUT =
            "9\r\n" + "-100\r\n" + "50\r\n" + "0\r\n" + "56.6\r\n" + "90\r\n"
                + "0.12\r\n" + ".12\r\n" + "02.34\r\n" + "000.000";

        final String EXPECTED_OUTPUT =
            "9056.65002.340.12.120000.000-100";

        String[] stringItems = BigDecimalStringSort
            .getSortedStringItems(TEST_INPUT);
        String actualOutput = "";
        // Output
        for (int index = 0; index < stringItems.length; index++) {
            System.out.println(stringItems[index]);
            actualOutput += stringItems[index];
        }

        Assert.assertEquals(EXPECTED_OUTPUT, actualOutput);
    }

}
