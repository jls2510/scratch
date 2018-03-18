package com.ping23.hr_exercises;

public class FindOdds
{

    public FindOdds()
    {
    }

    public static void main(String[] args)
    {
        int leftBound = -63;
        int rightBound = 63;
        int[] result = findOdds(leftBound, rightBound);
        System.out.println("findOdds(" + leftBound + ", " + rightBound + ") = "
            + formatArray(result));

    }

    private static String formatArray(int[] arrayToFormat)
    {

        String returnString = "{";

        for (int index = 0; index < arrayToFormat.length; index++)
        {
            returnString += arrayToFormat[index];
            if (index < arrayToFormat.length - 1)
            {
                returnString += ",";
            }

        }
        returnString += "}";
        return returnString;
    }

    static int[] findOdds(int l, int r)
    {
        // edge cases
        if (l==r) {
            if (l%2 != 0) {
                int[] rv = new int[1];
                rv[0] = l;
                return rv;
            }
            else {
                return new int[0];
            }
        }
        else if (l > r) {
            return new int[0];
        }
        
        int calcArrayLength = (int)Math.ceil((r - l + 1) / 2.0);
        
        int[] returnArray = new int[calcArrayLength];

        int arrayIndex = 0;
        for (int value = l; value <= r; value++)
        {
            if (value % 2 != 0)
            {
                returnArray[arrayIndex] = value;
                arrayIndex++;
            }
        }
        return returnArray;
    }

}
