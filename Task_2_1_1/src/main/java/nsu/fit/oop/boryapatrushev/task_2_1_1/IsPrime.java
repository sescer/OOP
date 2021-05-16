package nsu.fit.oop.boryapatrushev.task_2_1_1;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class IsPrime {
    /**
     * Function for checking if number is prime
     * Time complexity = O(sqrt(N))
     * @param number - number
     * @return true - if number is prime, otherwise - false
     */

    public static boolean prime(final Long number) {

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
