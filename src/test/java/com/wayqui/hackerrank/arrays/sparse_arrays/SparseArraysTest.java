package com.wayqui.hackerrank.arrays.sparse_arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

public class SparseArraysTest {

    @Test
    public void testingBasicData() {
        // Given
        String[] strings = new String[]{"ab", "ab", "abc"};
        String[] queries = new String[]{"ab", "abc", "bc"};

        // When
        int[] result = matchingStrings(strings, queries);

        // Then
        assertThat(result).isEqualTo(new int[]{2, 1, 0});

    }

    @Test
    public void testingSampleInput3() {
        // Given
        String[] strings = new String[]{"abcde", "sdaklfj", "asdjf", "na", "basdn", "sdaklfj", "asdjf", "na", "asdjf", "na", "basdn", "sdaklfj", "asdjf"};
        String[] queries = new String[]{"abcde", "sdaklfj", "asdjf", "na", "basdn"};

        // When
        int[] result = matchingStrings(strings, queries);

        // Then
        assertThat(result).isEqualTo(new int[]{1, 3, 4, 3, 2});
    }


    static int[] matchingStrings(String[] strings, String[] queries) {
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            for (String aString : strings) {
                if (aString.equals(queries[i])) result[i]++;
            }
        }

        // Using streams works but doesn't return the elements in the order that the query list has
        // for instance, the iterative algorithm returns {1, 3, 4, 3, 2}
        // the stream shows {asdjf=4, na=3, abcde=1, basdn=2, sdaklfj=3}
        Map<String, Long> results = Arrays
                .stream(strings)
                .filter(element ->  Arrays.asList(queries).contains(element))
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(results);

        return result;
    }
}
