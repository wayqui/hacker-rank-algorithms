package com.wayqui.qualified.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberToOrdinalTest {

    private static final String FIRST_SUFFIX = "st";
    private static final String SECOND_SUFFIX = "nd";
    private static final String THIRD_SUFFIX = "rd";
    private static final String REST_OF_SUFFIXES = "th";

    @Test
    public void test() {
        assertThat(NumberToOrdinalTest.numberToOrdinal(1)).isEqualTo("1st");
        assertThat(NumberToOrdinalTest.numberToOrdinal(2)).isEqualTo("2nd");
        assertThat(NumberToOrdinalTest.numberToOrdinal(3)).isEqualTo("3rd");
        assertThat(NumberToOrdinalTest.numberToOrdinal(4)).isEqualTo("4th");
        assertThat(NumberToOrdinalTest.numberToOrdinal(21)).isEqualTo("21st");
        assertThat(NumberToOrdinalTest.numberToOrdinal(1001301)).isEqualTo("1001301st");

        assertThat(NumberToOrdinalTest.numberToOrdinal(2)).isEqualTo("2nd");
        assertThat(NumberToOrdinalTest.numberToOrdinal(202)).isEqualTo("202nd");
        assertThat(NumberToOrdinalTest.numberToOrdinal(230)).isEqualTo("230th");

        assertThat(NumberToOrdinalTest.numberToOrdinal(3)).isEqualTo("3rd");
        assertThat(NumberToOrdinalTest.numberToOrdinal(300)).isEqualTo("300th");
        assertThat(NumberToOrdinalTest.numberToOrdinal(33)).isEqualTo("33rd");

        assertThat(NumberToOrdinalTest.numberToOrdinal(0)).isEqualTo("0");
        assertThat(NumberToOrdinalTest.numberToOrdinal(10000)).isEqualTo("10000th");

        assertThat(NumberToOrdinalTest.numberToOrdinal(111)).isEqualTo("111th");
        assertThat(NumberToOrdinalTest.numberToOrdinal(11)).isEqualTo("11th");
    }

    public static String numberToOrdinal(Integer number) {
        if (number.equals(0)) return String.valueOf(number);

        if (isATeenNumber(number)) return number + REST_OF_SUFFIXES;
        if (number % 10 == 1) return number + FIRST_SUFFIX;
        if (number % 10 == 2) return number + SECOND_SUFFIX;
        if (number % 10 == 3) return number + THIRD_SUFFIX;

        return number + REST_OF_SUFFIXES;
    }

    private static boolean isATeenNumber(Integer number) {
        return (number % 100 >= 10 && number % 100 <= 19);
    }

}