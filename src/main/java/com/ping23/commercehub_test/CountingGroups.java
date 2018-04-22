package com.ping23.commercehub_test;

import java.io.*;
import java.util.*;

public class CountingGroups
{

    /*
     * Complete the function below.
     */
    static int[] countGroups(int[][] matrix, int[] testCases)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // track the next group ID
        int nextGroupID = 100;

        // iterate over the matrix
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                // for each cell

                // if cell value == 0 then do nothing
                if (matrix[row][col] == 0)
                {
                    continue;
                }

                // is cell value == 1? ------------------------------
                if (matrix[row][col] == 1)
                {
                    // look to the right
                    int right = 0;
                    if (col < (cols - 1))
                    {
                        right = matrix[row][col + 1];
                    }

                    // is right already in an existing group?
                    if (right > 1)
                    {
                        // right is already in an existing group
                        // so I will be in same existing group
                        matrix[row][col] = right;
                        //System.out.println("setting self " +(row) + "," + col + " to existing group " + matrix[row][col]); 
                    }

                    // right is not in an exiting group
                    // so I am creating a new group
                    else
                    {
                        // set my group ID
                        matrix[row][col] = nextGroupID;
                        //System.out.println("setting self " +(row) + "," + col + " to new group " + matrix[row][col]); 
                        // check right
                        if (right == 1)
                        {
                            // right is also a 1
                            // so we will both be in same new group
                            matrix[row][col + 1] = nextGroupID;
                            //System.out.println("setting right " +(row) + "," + (col+1) + " to new group " + matrix[row][col]); 
                        }
                        // end of new group, so increment conunter for next
                        // new group
                        nextGroupID++;
                    }

                } // end cell value == 1 ------------------------------

                // am I already in an existing group
                // ------------------------------------
                else if (matrix[row][col] > 1) // comparison is redundant here but safer for later refactoring
                {
                    // look to the right
                    if (col < (cols - 1))
                    {
                        int right = matrix[row][col + 1];

                        if (right > 1)
                        {
                            // I am in an existing group and
                            // right is in an existing group
                            // so we SHOULD ALREADY BE in same existing group
                            if (!(matrix[row][col] == right))
                            {
                                // handle error
                                System.out.println("error");
                            }
                        }

                        else
                        {
                            // I am in an existing group
                            if (right == 1)
                            {
                                // right will be in the same existing group
                                matrix[row][col + 1] = matrix[row][col];
                                //System.out.println("setting right " +(row) + "," + (col+1) + " to group " + matrix[row][col]); 
                            }
                        }
                    } // end look to the right -------

                } // end already in existing group
                  // ---------------------------------------

                // new group OR existing group
                // (already exited loop if cell value == 0)
                // look below (below can't already be in a group)
                // -------------------------
                if (row < (rows - 1))
                {
                    // if it's a 1 then below is in the same group as I
                    if (matrix[row + 1][col] == 1)
                    {
                        matrix[row + 1][col] = matrix[row][col];
                        //System.out.println("setting below " +(row+1) + "," + col + " to group " + matrix[row][col]); 
                    }
                } // end look below -------------------------------------

            } // end cols
        } // end rows

        // now we have to find and tally the groups
        // this could be done above when optimizing, but I'm doing it this way
        // for the sake of clarity

        // nextGroupID should NOW equal the total number of groups (+100)
        int[] groupTallies = new int[nextGroupID - 100];
        // iterate over the matrix again
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                // for each cell
                int thisVal = matrix[row][col];
                if (thisVal > 0)
                {
                    int groupIndex = thisVal - 100;
                    groupTallies[groupIndex]++;
                }
            }
        }

        // now tally the tallies
        // testCases and testCaseResults will be the same length
        int[] testCaseResults = new int[testCases.length];
        for (int tcIndex = 0; tcIndex < testCases.length; tcIndex++)
        {
            for (int gtIndex = 0; gtIndex < groupTallies.length; gtIndex++)
            {
                if (groupTallies[gtIndex] == testCases[tcIndex])
                {
                    testCaseResults[tcIndex]++;
                }
            }
        }

        return testCaseResults;

    } // end countGroups()

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(INPUT_1);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null)
        {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else
        {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int[] res;
        int m_rows = 0;
        int m_cols = 0;
        m_rows = Integer.parseInt(in.nextLine().trim());
        m_cols = Integer.parseInt(in.nextLine().trim());

        int[][] m = new int[m_rows][m_cols];
        for (int m_i = 0; m_i < m_rows; m_i++)
        {
            for (int m_j = 0; m_j < m_cols; m_j++)
            {
                m[m_i][m_j] = in.nextInt();

            }
        }

        if (in.hasNextLine())
        {
            in.nextLine();
        }

        int t_size = 0;
        t_size = Integer.parseInt(in.nextLine().trim());

        int[] t = new int[t_size];
        for (int i = 0; i < t_size; i++)
        {
            int t_item;
            t_item = Integer.parseInt(in.nextLine().trim());
            t[i] = t_item;
        }

        res = countGroups(m, t);
        for (int res_i = 0; res_i < res.length; res_i++)
        {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
        in.close();
    }

    private static final String INPUT_1 = "10\r\n" + "10\r\n"
        + "1 1 1 1 1 1 1 1 1 1\r\n" + "1 1 1 1 0 0 0 0 0 0\r\n"
        + "1 1 1 0 0 0 0 1 1 1\r\n" + "1 1 0 0 1 0 0 1 1 1\r\n"
        + "1 0 1 0 0 1 1 0 0 0\r\n" + "0 0 0 0 0 0 0 0 0 0\r\n"
        + "0 0 0 0 0 0 0 0 0 0\r\n" + "1 1 1 1 1 1 1 1 1 1\r\n"
        + "0 0 0 0 0 0 0 0 0 0\r\n" + "1 1 1 1 1 1 1 1 1 1\r\n" + "5\r\n"
        + "1\r\n" + "10\r\n" + "20\r\n" + "2\r\n" + "6";

    private static final String INPUT_2 = "5\r\n" + "5\r\n" + "1 0 1 1 0\r\n"
        + "0 1 0 0 1\r\n" + "1 0 1 1 0\r\n" + "1 0 1 1 0\r\n" + "0 1 0 0 1\r\n"
        + "5\r\n" + "1\r\n" + "2\r\n" + "3\r\n" + "4\r\n" + "5";

}
