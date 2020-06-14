package com.wayqui.hackerrank.arrays.moveelements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Performs the change of positions between elements within an array of any type.
 * @param <T> Data type for the list of elements. For complex types the equals method must be implemented
 */
public class MoveElements<T> {

    private List<T> elements;

    public MoveElements(List<T> elements) {
        this.elements = elements;
    }
    /**
     * Move items from this array the number of positions indicated as a parameter
     * @param positions number of positions for switching (positive to the right and negative
     *                  to the left).
     * @return new array with the elements moved
     */
    public List<T> moveItems(Integer positions) {
        if (elements == null || elements.size() == 0) return elements;
        int validPositions = positions % elements.size();
        if (validPositions == 0) return elements;
        return switchPosition(validPositions);
    }

    private List<T> switchPosition(int validPositions) {
        AtomicInteger i = new AtomicInteger();
        List<T> newArray = new ArrayList<>();
        elements.forEach(element -> {
            final int position = (i.get() + elements.size() - validPositions) % elements.size();
            newArray.add(elements.get(position));
            i.getAndIncrement();
        });
        return newArray;
    }

    public int[] switchPositionsOnArrays(int[] input, int positions) {
        int[] output = new int[input.length];

        if (input.length == 0) return input;
        int validPositions = positions % input.length;
        if (validPositions == 0) return input;

        for (int i = 0; i < input.length; i++) {
            int position = (i + input.length - validPositions) % input.length;
            output[i] = input[position];
        }
        return output;
    }
}
