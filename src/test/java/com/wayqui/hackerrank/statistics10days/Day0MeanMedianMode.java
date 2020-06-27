package com.wayqui.hackerrank.statistics10days;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Day0MeanMedianMode {

    @Test
    public void testDay0MeanMedianMode() {
        // 1 of 3
        List<Integer> elements = Arrays.asList(64630, 11735, 14216, 99233, 14470, 4978, 73429, 38120, 51135, 67060);

        assertThat(calculateMean(elements)).isEqualTo(43900.6);
        assertThat(calculateMedian(elements)).isEqualTo(44627.5);
        assertThat(calculateMode(elements)).isEqualTo(4978);

        elements = Arrays.asList(3, 7, 8, 5, 12, 14, 21, 13, 18);
        assertThat(calculateMedian(elements)).isEqualTo(12);

        // 2 of 3
        List<Integer> weightedElements = Arrays.asList(10, 40, 30, 50, 20);
        List<Integer> weights = Arrays.asList(1, 2, 3, 4, 5);

        assertThat(calculateWeightedMean(weightedElements, weights)).isEqualTo(32.0);

        weightedElements = Arrays.asList(10, 40, 30, 50, 20, 10, 40, 30, 50, 20);
        weights = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThat(calculateWeightedMean(weightedElements, weights)).isEqualTo(31.09090909090909);
    }

    private double calculateMean(List<Integer> elements) {
        return elements.stream().mapToLong(Integer::intValue).average().orElse(0);
    }

    private double calculateWeightedMean(List<Integer> elements, List<Integer> weights) {
        int accumulated = 0;
        int sumOfWeights = 0;
        for (int i = 0; i < elements.size(); i++) {
            accumulated += elements.get(i) * weights.get(i);
            sumOfWeights += weights.get(i);
        }

        return (double) accumulated / sumOfWeights;
    }

    private double calculateMedian(List<Integer> elements) {
        ArrayList<Integer> sortedList = elements
                .stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        final int size = sortedList.size();
        if (size % 2 == 0) {
            return (double) (sortedList.get((size / 2) - 1) + sortedList.get(size / 2)) / 2;
        } else {
            return (double) sortedList.get((size / 2));
        }
    }

    private int calculateMode(List<Integer> elements) {
        Map<Integer, Long> result = elements
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxRepetitions = result.values().stream().mapToLong(Long::longValue).max().orElse(0L);

        AtomicReference<Integer> mode = new AtomicReference<>(Integer.MAX_VALUE);

        result.forEach((k, v) -> {
            if (v.equals(maxRepetitions)) {
                if (mode.get() > k) {
                    mode.set(k);
                }
            }
        });

        return mode.get();
    }
}