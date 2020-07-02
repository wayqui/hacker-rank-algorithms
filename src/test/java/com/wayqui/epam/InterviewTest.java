package com.wayqui.epam;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

public class InterviewTest {

    @Test
    public void given_a_string_When_counting_words_Then_return_string_sorted_by_character_frequency() {
        // Given a string
        String data = "test";

        // When we process the string and calculate its statistics
        String result = generateStringBasedOnStatistics(data); 

        // Then a new string is generated with the same characters sorted by its frequency.
        assertThat(result).satisfiesAnyOf(
                a -> assertThat(a).isEqualTo("ttes"),
                a -> assertThat(a).isEqualTo("ttse")
        );
    }

    private String generateStringBasedOnStatistics(String data) {
        // Group by character and count statistics
        Map<String, Long> statistics = data
                .codePoints()
                .mapToObj(c -> Character.toString((char) c))
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        // Sort by frequency (first the element with most repetitions)

        Map<String, Long> sortedStats = statistics.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // Generate string with the statistics.
        AtomicReference<String> result = new AtomicReference<>("");
        sortedStats.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                result.set(result + k);
            }
        });

        return result.get();
    }
}
