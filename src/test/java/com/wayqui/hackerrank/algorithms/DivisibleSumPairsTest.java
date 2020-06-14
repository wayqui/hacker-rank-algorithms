package com.wayqui.hackerrank.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisibleSumPairsTest {

    @Test
    public void testing() {

        int n = 6;
        int k = 3;
        int[] ar = new int[]{1, 3, 2, 6, 1, 2};

        int result = DivisibleSumPairsTest.divisibleSumPairs(n, k, ar);

        assertThat(result).isEqualTo(5);

    }


    static int divisibleSumPairs(int n, int k, int[] ar) {
        int numDivisors = 0;

        for (int i = 0; i < ar.length-1; i++) {
            for (int j = i+1; j < ar.length; j++) {
                if ((ar[i] + ar[j]) % k == 0)
                    numDivisors++;
            }
        }

        return numDivisors;
    }

}
