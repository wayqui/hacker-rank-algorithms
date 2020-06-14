package com.wayqui.hackerrank.arrays.moveelements;

import com.wayqui.hackerrank.arrays.moveelements.bean.MyObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MoveElementsTest {

    @Test
    public void given_string_array_when_moving_elements_then_array_switched_correctly() {
        // Given
        final List<String> inputElements = Arrays.asList("a", "b", "c", "d");
        MoveElements<String> elementsToMove = new MoveElements<>(inputElements);

        // When
        List<String> result = elementsToMove.moveItems(5);

        // Then
        assertThat(result.size()).isEqualTo(inputElements.size());
        assertThat(result).isEqualTo(Arrays.asList("d", "a", "b", "c"));
    }

    @Test
    public void given_different_arrays_when_moving_elements_then_array_switched_correctly () {
        // Given
        final List<String> strings = Arrays.asList("a", "b", "c", "d");
        final List<Long> longs = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L);
        final List<MyObject> myObjects = Arrays.asList(
                new MyObject("jose", "jose@testing.com"),
                new MyObject("alex", "alex@testing.com"),
                new MyObject("adai", "adai@testing.com")
        );

        MoveElements<String> moveStrings = new MoveElements<>(strings);
        MoveElements<Long> moveLongs = new MoveElements<>(longs);
        MoveElements<MyObject> moveMyObjects = new MoveElements<>(myObjects);

        // When
        List<String> newStrings = moveStrings.moveItems(3577);
        List<Long> newLongs = moveLongs.moveItems(504);
        List<MyObject> newMyObjects = moveMyObjects.moveItems(-250);

        // Then
        assertThat(newStrings).isEqualTo(Arrays.asList("d", "a", "b", "c"));
        assertThat(newLongs).isEqualTo(longs);
        assertThat(newMyObjects).isEqualTo(Arrays.asList(
                new MyObject("alex", "alex@testing.com"),
                new MyObject("adai", "adai@testing.com"),
                new MyObject("jose", "jose@testing.com")
        ));
    }
}