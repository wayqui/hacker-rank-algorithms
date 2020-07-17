package com.wayqui.hackerrank.arrays.array_manipulation;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.OptionalLong;
import com.wayqui.demo.dto.PersonDto;
import com.wayqui.demo.entity.Person;
import com.wayqui.demo.mapper.PersonMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayManipulationTest {

    @Test
    public void basicTest(){
        int n = 4000;
        int m = 30000;
        int[][] queries = new int[m][3];
        queries[0] = new int[]{1,5,3};
        queries[1] = new int[]{4,8,7};
        queries[2] = new int[]{6,9,1};
        
        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void testCase4Failed() throws IOException {
        int n = 4000;
        int m = 30000;

        int[][] queries = loadFromFile(m, "data4.txt");

        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(7542539201L);
    }

    /**
     * FIXME This test fails after 18 minutes due to excessive resource consumption
     * @throws IOException
     */
    @Test
    @Ignore
    public void testCase9Failed() throws IOException {
        int n = 10000000;
        int m = 100000;

        int[][] queries = loadFromFile(m, "data9.txt");

        long result = ArrayManipulationTest.arrayManipulation(n, queries);

        assertThat(result).isEqualTo(2501448788L);
    }

    private int[][] loadFromFile(int m, String resourceName) throws IOException {

        int[][] queries = new int[m][3];

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int i = 0;
        while ((st = br.readLine()) != null) {
            String[] result = st.split(" ");
            queries[i][0] = Integer.parseInt(result[0]);
            queries[i][1] = Integer.parseInt(result[1]);
            queries[i][2] = Integer.parseInt(result[2]);
            i++;
        }

        return queries;
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long[] zeroArray = new long[n];

        long maxElement = 0;

        // Iterate over a list of queries
        for (int[] query : queries) {
            // accumulate each result in the array of zeroes
            //printArray(query);
            for (int j = query[0] - 1; j <= query[1] - 1; j++) {
                zeroArray[j] = zeroArray[j] + query[2];
            }
            //printArray(zeroArray);
        }
        //printArray(zeroArray);

        // Obtain the max value of the result
        OptionalLong result = Arrays.stream(zeroArray).max();
        return result.orElse(0L);
    }

    static long arrayManipulationImprovement(int n, int[][] queries) {

        return 0;
    }


    private static void printArray(long[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    
    @Test
    public void repeated_string() {
        String s = "aedbdaeaddadddcedcbbabdccbecaecaccdbebeeadadcaabbaabbaeeeecaddbcdecbbdccdebaaebecdaaabbcdeccbabaabce";
        long n = 731869010806L;

        
        long repetitions = n / s.length();
        long remaining = n % s.length();

        long repeatedAes = s.chars().filter(ch -> ch == 'a').count() * repetitions;
        repeatedAes = repeatedAes + s.substring(0, (int) remaining)
                .chars().filter(ch -> ch == 'a').count();

        System.out.println("s: "+s);
        System.out.println("s (size): "+s.length());
        System.out.println("n: "+n);
        System.out.println("#aes in s: "+s.chars().filter(ch -> ch == 'a').count());
        System.out.println("repetitions: "+repetitions);
        System.out.println("remaining: "+remaining);
        System.out.println("repeatedAes: "+repeatedAes);

        assertThat(repeatedAes).isEqualTo(168329872486L);
    }

    @Test
    public void makeAnagrams() {
        assertThat(MockData.makeAnagram("cde", "dcff")).isEqualTo(3);
    }

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        a = a.trim();
        b = b.trim();
        if (a.equals(b)) {
            //System.out.println("The strings are equal");
            return 0;
        }
        String sortedA = a.chars().sorted().toString();
        String sortedB = b.chars().sorted().toString();
        if (sortedA.equals(sortedB)) {
            //System.out.println("The sorted strings are equal");
            return 0;
        }

        Map<Character, Integer> aStatistics = countCharsRepetitions(a);
        Map<Character, Integer> bStatistics = countCharsRepetitions(b);

        System.out.println("Frequencies a:\n" + aStatistics);
        System.out.println("Frequencies b:\n" + bStatistics);

        if (aStatistics == bStatistics) {
            //System.out.println("The statistics for both strings are equal");
            return 0;
        }

        Integer minRemovals = findDifferences(aStatistics, bStatistics);
        minRemovals += findDifferences(bStatistics, aStatistics);

        return minRemovals;
    }

    private static Integer findDifferences(Map<Character, Integer> aStatistics, Map<Character, Integer> bStatistics) {
        AtomicReference<Integer> counting = new AtomicReference<>(0);

        aStatistics.forEach((k , v) -> {
            if (bStatistics.get(k) == null) {
                counting.getAndSet(counting.get() + v);
            } else if (bStatistics.get(k) > v) {
                counting.getAndSet(counting.get() + (bStatistics.get(k) - v));
            }
        });
        return counting.get();
    }

    private static Map<Character, Integer> countCharsRepetitions(String a) {
        return a.chars().boxed()
                .collect(toMap(
                        k -> (char) k.intValue(),
                        v -> 1,
                        Integer::sum));
    }

    @Test
    public void alternatingCharacters() {
        assertThat(MockData.alternatingCharacters("AAAA")).isEqualTo(3);
        assertThat(MockData.alternatingCharacters("BBBBB")).isEqualTo(4);
        assertThat(MockData.alternatingCharacters("ABABABAB")).isEqualTo(0);
        assertThat(MockData.alternatingCharacters("BABABA")).isEqualTo(0);
        assertThat(MockData.alternatingCharacters("AAABBB")).isEqualTo(4);
    }

    static int alternatingCharacters(String s) {
        char[] charArray = s.toCharArray();
        int erased = 0;
        for (int i = 0; i < charArray.length-1; i++) {
            if (charArray[i] == charArray[i+1]) erased++;
        }
        return erased;
    }

    @Test
    public void isValid() {
        assertThat(MockData.isValid("abbccc")).isEqualTo("YES");
        //assertThat(MockData.isValid("abcc")).isEqualTo("YES");
        //assertThat(MockData.isValid("abccc")).isEqualTo("NO");
        //assertThat(MockData.isValid("abcdefghhgfedecba")).isEqualTo("YES");
        //assertThat(MockData.isValid("abcdefghhgfedecbaf")).isEqualTo("NO");
    }

    static String isValid(String s) {
        Map<Character, Integer> frequencies = countCharsRepetitions(s);

        List<Integer> sortedFreq = frequencies.values().stream().sorted().collect(Collectors.toList());





        List<Integer> result = frequencies.values().stream().distinct().collect(Collectors.toList());

        long distinctChars = s.trim().toLowerCase().chars().boxed().distinct().count();
        if (distinctChars == 0) return "YES";
        long extraCharacters = s.length() % distinctChars;
        if (extraCharacters > 1) return "NO";
        return "YES";
    }
}
