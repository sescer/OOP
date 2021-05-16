package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Main {
     static final int CONVERSION_IN_SEC = 1000;
    public static void main(final String[] args) throws FileNotFoundException {

        ArrayList<Long> arr = readFile(args[0]);
        final long startTime = System.currentTimeMillis();
        if (Integer.parseInt(args[2]) == 1) {
            System.out.println("Thread pool");
            ParallelThreadSolution.primeArray(arr, Integer.parseInt(args[1]));
        } else if (Integer.parseInt(args[2]) == 2) {
            System.out.println("Parallel stream");
            ParallelStreamSolution.primeArray(arr);
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime)
                / CONVERSION_IN_SEC + "." + (endTime - startTime)
                % CONVERSION_IN_SEC);
    }

    private static ArrayList<Long> readFile(final String path)
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
}
