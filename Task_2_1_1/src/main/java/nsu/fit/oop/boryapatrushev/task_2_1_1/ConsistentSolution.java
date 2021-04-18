package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.util.ArrayList;

public class ConsistentSolution {
    /**
    * Consistent solution
     * @param arr - array with preinitialized values
     * @return true - if array contains at least one non-prime number, otherwise - false
     */
    public static boolean PrimeArray(ArrayList<Long> arr) {

        for (Long num: arr) {
            if(!IsPrime.prime(num)) {
                return true;
            }
        }
        return false;
    }
}
