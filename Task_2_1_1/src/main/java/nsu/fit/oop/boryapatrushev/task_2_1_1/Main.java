package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Long> arr = ReadFile(args[0]);
        final long startTime = System.currentTimeMillis();
        if(Integer.parseInt(args[2]) == 1) {
            System.out.println("Thread pool");
            ParallelThreadSolution.PrimeArray(arr, Integer.parseInt(args[1]));
        }
        else if(Integer.parseInt(args[2]) == 2) {
            System.out.println("Parallel stream");
            ParallelStreamSolution.PrimeArray(arr);
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) / 1000 + "." + (endTime - startTime) % 1000);
    }

    private static ArrayList<Long> ReadFile(String path) throws FileNotFoundException {

        Scanner input = new Scanner(new File(path));
        int cnt = input.nextInt();
        long num;
        ArrayList<Long> arr = new ArrayList<>();

        for(int i = 0; i!= cnt; i++) {
            num = input.nextLong();
            arr.add(num);
        }
        return arr;
    }
}
