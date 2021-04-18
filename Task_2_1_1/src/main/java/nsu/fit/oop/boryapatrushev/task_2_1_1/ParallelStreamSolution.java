package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * ParallelStream Solution
 * Number of threads predefined by number of logical cores at user PC
 */
public class ParallelStreamSolution {
    public static boolean PrimeArray(ArrayList<Long> arr) {
        return arr.parallelStream().map(IsPrime::prime).collect(Collectors.toList()).contains(false);
    }
}
