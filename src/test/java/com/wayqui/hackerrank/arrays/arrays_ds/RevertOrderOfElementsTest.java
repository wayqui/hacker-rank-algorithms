package com.wayqui.hackerrank.arrays.arrays_ds;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RevertOrderOfElementsTest {

    @Test
    public void revertElementsTesting() {
        int[] a = new int[]{2, 4, 0, 5, 6, 3, 7, 96, 24, 12};

        int[] result= new int[a.length];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[a.length - i - 1];
        }

        assertThat(result).isEqualTo(new int[]{12, 24, 96, 7, 3, 6, 5, 0, 4, 2});
    }
}
