package com.wayqui.hackerrank.statistics10days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1QuartilesTest {

    @Test
    public void testTutorial() {
        List<Integer> elements = Arrays.asList(98, 90, 70, 18, 92, 92, 55, 83, 45, 95, 88, 76);

        final List<Double> doubles = calculateQuartiles(elements);
        assertThat(doubles.get(0)).isEqualTo(62.5);
        assertThat(doubles.get(1)).isEqualTo(85.5);
        assertThat(doubles.get(2)).isEqualTo(92);
    }

    @Test
    public void testQuartiles() {
        List<Integer> elements = Arrays.asList(3, 7, 8, 5, 12, 14, 21, 13, 18);

        final List<Double> doubles = calculateQuartiles(elements);

        System.out.println(Arrays.toString(doubles.toArray()));

        assertThat(doubles.get(0)).isEqualTo(6);
        assertThat(doubles.get(1)).isEqualTo(12);
        assertThat(doubles.get(2)).isEqualTo(16);
    }

    @Test
    public void testInterQuartileRange() {
        List<Integer> elements = Arrays.asList(6, 12, 8, 10, 20, 16);
        List<Integer> frequencies = Arrays.asList(5, 4, 3, 2, 1, 5);

        List<Integer> replicatedElements = replicateElements(elements, frequencies);
        double iqRange = calculateInterQuartileRange(replicatedElements);

        assertThat(iqRange).isEqualTo(9.0);
    }

    @Test
    public void testInterQuartileRangeTestCase1() {
        List<Integer> elements = Arrays.asList(6, 12, 8, 10, 20, 16);
        List<Integer> frequencies = Arrays.asList(5, 6, 7, 8, 9, 10);
        List<Integer> replicatedElements = replicateElements(elements, frequencies);

        double iqRange = calculateInterQuartileRange(replicatedElements);

        assertThat(iqRange).isEqualTo(8);
    }

    @Test
    public void testInterQuartileRangeTestCase4() {
        List<Integer> elements = Arrays.asList(10, 40, 30, 50, 20, 10, 40, 30, 50, 20);
        List<Integer> frequencies = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> replicatedElements = replicateElements(elements, frequencies);

        double iqRange = calculateInterQuartileRange(replicatedElements);

        assertThat(iqRange).isEqualTo(20.0);
    }

    @Test
    public void testInterQuartileRangeTestCase5() {
        List<Integer> elements = Arrays.asList(10, 40, 30, 50, 20);
        List<Integer> frequencies = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> replicatedElements = replicateElements(elements, frequencies);

        double iqRange = calculateInterQuartileRange(replicatedElements);

        assertThat(iqRange).isEqualTo(30.0);
    }

    @Test
    public void testInterQuartileRangeKhan() {
        List<Integer> elements = Arrays.asList(7, 9, 10, 11, 12, 14);
        List<Integer> frequencies = Arrays.asList(1, 2, 3, 1, 2, 1);
        List<Integer> replicatedElements = replicateElements(elements, frequencies);

        double iqRange = calculateInterQuartileRange(replicatedElements);

        assertThat(iqRange).isEqualTo(3);
    }

    private List<Integer> replicateElements(List<Integer> elements, List<Integer> frequencies) {
        List<Integer> replicatedElements = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < frequencies.get(i); j++) {
                replicatedElements.add(elements.get(i));
            }
        }

        return replicatedElements;
    }

    private double calculateMedian(List<Integer> elements) {
        final int size = elements.size();
        if (size % 2 == 0) {
            return (double) (elements.get((size / 2) - 1) + elements.get(size / 2)) / 2;
        } else {
            return (double) elements.get((size / 2));
        }
    }

    private List<Double> calculateQuartiles(List<Integer> elements) {

        ArrayList<Integer> sortedList = elements
                .stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(Arrays.toString(sortedList.toArray()));

        double q2 = calculateMedian(sortedList);

        int i = 0;
        ArrayList<Integer> firstQList = new ArrayList<>();
        while (i < sortedList.size() / 2) {
            firstQList.add(sortedList.get(i));
            i++;
        }

        System.out.println(Arrays.toString(firstQList.toArray()));

        double q1 = calculateMedian(firstQList);

        int j = (sortedList.size() / 2) +1;
        ArrayList<Integer> thirdQList = new ArrayList<>();
        while (j < sortedList.size()) {
            thirdQList.add(sortedList.get(j));
            j++;
        }

        System.out.println(Arrays.toString(thirdQList.toArray()));

        double q3 = calculateMedian(thirdQList);


        return Arrays.asList(q1, q2, q3);

    }

    private double calculateInterQuartileRange(List<Integer> elements) {
        List<Double> quartiles = calculateQuartiles(elements);
        return quartiles.get(2) - quartiles.get(0);
    }

}
