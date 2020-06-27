package com.wayqui.hackerrank.statistics10days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1StandardDeviation {

    @Test
    public void testingDeviation() {
        List<Integer> elements = Arrays.asList(10, 40, 30, 50, 20);

        double stdDev = Day1StandardDeviation.standardDeviation(elements);

        assertThat(Math.round(stdDev*10)/10.0).isEqualTo(14.1);
    }

    private static double standardDeviation(List<Integer> elements) {
        double mean = elements.stream().mapToInt(Integer::intValue).average().orElse(0);

        if (mean == 0) return mean;

        return Math.sqrt(summarizeSquaredDistanceToMean(elements, mean)/elements.size());

    }

    private static double summarizeSquaredDistanceToMean(List<Integer> elements, double mean) {
        return elements.stream()
                .mapToDouble(element -> Math.pow(element - mean, 2))
                .sum();
    }
}
