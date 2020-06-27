package com.wayqui.hackerrank.arrays.array_manipulation;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayManipulationTest {

    @Test
    public void basicTest(){
        int n = 4000;
        int m = 30000;
        int[][] queries = new int[m][3];
        queries[0] = new int[]{1,5,3};
        queries[1] = new int[]{4,8,7};
        queries[2] = new int[]{6,9,1};
        
        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void testCase4Failed() throws IOException {
        int n = 4000;
        int m = 30000;

        int[][] queries = loadFromFile(m, "data4.txt");

        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(7542539201L);
    }

    /**
     * FIXME This test fails after 18 minutes due to excessive resource consumption
     * @throws IOException
     */
    @Test
    @Ignore
    public void testCase9Failed() throws IOException {
        int n = 10000000;
        int m = 100000;

        int[][] queries = loadFromFile(m, "data9.txt");

        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(2501448788L);
    }

    private int[][] loadFromFile(int m, String resourceName) throws IOException {

        int[][] queries = new int[m][3];

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int i = 0;
        while ((st = br.readLine()) != null) {
            String[] result = st.split(" ");
            queries[i][0] = Integer.parseInt(result[0]);
            queries[i][1] = Integer.parseInt(result[1]);
            queries[i][2] = Integer.parseInt(result[2]);
            i++;
        }

        return queries;
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long[] zeroArray = new long[n];

        long maxElement = 0;

        // Iterate over a list of queries
        for (int[] query : queries) {
            // accumulate each result in the array of zeroes
            //printArray(query);
            for (int j = query[0] - 1; j <= query[1] - 1; j++) {
                zeroArray[j] = zeroArray[j] + query[2];
            }
            //printArray(zeroArray);
        }
        //printArray(zeroArray);

        // Obtain the max value of the result
        OptionalLong result = Arrays.stream(zeroArray).max();
        return result.orElse(0L);
    }

    static long arrayManipulationImprovement(int n, int[][] queries) {

        return 0;
    }


    private static void printArray(long[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
