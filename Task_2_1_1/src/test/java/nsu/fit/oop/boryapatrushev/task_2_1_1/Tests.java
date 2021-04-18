package nsu.fit.oop.boryapatrushev.task_2_1_1;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tests {

    static final int NUM_OF_WAYS = 3;
    static final int THREAD_NO = 8;

    private final ArrayList<Long> arr1 = new ArrayList<>(
                Arrays.asList(6L, 8L, 7L, 13L, 9L, 4L));

    private final ArrayList<Long> arr2 = new ArrayList<>(
            Arrays.asList(6997901L, 6997927L, 6997937L, 6997967L, 6998009L,
                    6998029L, 6998039L, 6998051L, 6998053L));

    private ArrayList<Long> readFile(final String path)
            throws FileNotFoundException {

        Scanner input = new Scanner(new File(path));
        int cnt = input.nextInt();
        long num;
        ArrayList<Long> arr = new ArrayList<>();

        for (int i = 0; i != cnt; i++) {
            num = input.nextLong();
            arr.add(num);
        }
        return arr;
    }

    @Test
    public void test1() {

        boolean[] answers = new boolean[NUM_OF_WAYS];

        answers[0] = ConsistentSolution.primeArray(arr1);
        answers[1] = ParallelThreadSolution.primeArray(arr1, THREAD_NO);
        answers[2] = ParallelStreamSolution.primeArray(arr1);

        for (int i = 0; i != answers.length; i++) {
            try {
                Assert.assertTrue(answers[i]);
            } catch (AssertionError e) {
                throw new AssertionError("Incorrect answer at index: " + i);
            }
        }
    }

    @Test
    public void test2() {

        boolean[] answers = new boolean[NUM_OF_WAYS];

        answers[0] = ConsistentSolution.primeArray(arr2);
        answers[1] = ParallelThreadSolution.primeArray(arr2, THREAD_NO);
        answers[2] = ParallelStreamSolution.primeArray(arr2);

        for (int i = 0; i != answers.length; i++) {
            try {
                Assert.assertFalse(answers[i]);
            } catch (AssertionError e) {
                throw new AssertionError("Incorrect answer at index: " + i);
            }
        }
    }


    // -------------------------------------------------------------
    // Time results for sequential and parallel solutions are
    // given below (intel i7 10510u - 4(8) cores)
    // Template of tests description:
    // [Time] - <optional>[NoThreads] ([Size of input array])


    // Consistent solution
    // 4.364 (600)
    @Test
    public void test3() throws FileNotFoundException {

        ArrayList<Long> arr = readFile("src/test/resources/primes.txt");
        boolean answer = ConsistentSolution.primeArray(arr);
        Assert.assertFalse(answer);
    }

    // Parallel solution using ThreadPools
    // 2.262 - 2 threads (600)
    // 1.698 - 3 threads (600)
    // 1.539 - 4 threads (600)
    // 1.318 - 5 threads (600)
    // 1.126 - 8 threads (600)
    // 1.14 - 20 threads (600)
    @Test
    public void test4() throws FileNotFoundException {

        ArrayList<Long> arr = readFile("src/test/resources/primes.txt");
        boolean answer = ParallelThreadSolution.primeArray(arr, THREAD_NO);
        Assert.assertFalse(answer);
    }

    // Parallel solution using ParallelStream
    // 1.0 -  threads (600)
    @Test
    public void test5() throws FileNotFoundException {

        ArrayList<Long> arr = readFile("src/test/resources/primes.txt");
        boolean answer = ParallelStreamSolution.primeArray(arr);
        Assert.assertFalse(answer);
    }


    // -------------------------------------------------------------
    // Time results for sequential and parallel solutions are
    // given below (intel i7 10510u - 4(8) cores)


    // Consistent solution
    // 20.802 (1000001)
    @Test
    public void test6() throws FileNotFoundException {

        ArrayList<Long> arr =
                readFile("src/test/resources/povezlo_povezlo.txt");
        boolean answer = ConsistentSolution.primeArray(arr);
        Assert.assertFalse(answer);
    }


    // Parallel solution using ThreadPools
    // 14.37 - 2 threads (1000001)
    // 13.581 - 3 threads (1000001)
    // 8.766 - 4 threads (1000001)
    // 7.819 - 5 threads (1000001)
    // 5.855 - 8 threads (1000001)
    // 5.106 - 20 threads (1000001)


    @Test
    public void test7() throws FileNotFoundException {

        ArrayList<Long> arr =
                readFile("src/test/resources/povezlo_povezlo.txt");
        boolean answer = ParallelThreadSolution.primeArray(arr, THREAD_NO);
        Assert.assertFalse(answer);
    }

    // Parallel solution using ParallelStream
    // 5.334 -  threads (1000001)

    @Test
    public void test8() throws FileNotFoundException {

        ArrayList<Long> arr =
                readFile("src/test/resources/povezlo_povezlo.txt");
        boolean answer = ParallelStreamSolution.primeArray(arr);
        Assert.assertFalse(answer);
    }
}
