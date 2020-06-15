package com.wayqui.hackerrank.algorithms;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

public class MigratoryBirdsTest {

    @Test
    public void testing() {
        // Given
        List<Integer> arr = Arrays.asList(1, 4, 4, 4, 5, 3);

        // When
        int result = MigratoryBirdsTest.migratoryBirds(arr);

        // Then
        assertThat(result).isEqualTo(4);
    }

    static int migratoryBirds(List<Integer> arr) {
        Map<Integer, Long> result = arr.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        Long maxSights = result.values().stream().max(Long::compare).orElse(0L);

        int minKey = 6;

        Iterator<Map.Entry<Integer, Long>> iterator = result.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Long> res = iterator.next();
            if (res.getValue().equals(maxSights)) {
                if (res.getKey() < minKey) {
                    minKey = res.getKey();
                }
            }
        }

        return minKey;
    }

    @Test
    public void practice() {
        System.out.println(MigratoryBirdsTest.sayHello("joselo"));
        System.out.println(MigratoryBirdsTest.sayHello(""));
        System.out.println(MigratoryBirdsTest.sayHello(null));
    }

    private static final String GREETING = "Hello{0}!";
    private static final String ANONYMOUS_GREET = " there";

    public static String sayHello( String name ) {
        if (name != null && !name.trim().isEmpty())
            return MessageFormat.format(GREETING, ", "+name);

        return MessageFormat.format(GREETING, ANONYMOUS_GREET);
    }
}
