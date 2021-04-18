package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelThreadSolution {
    /**
     * ParallelThread solution
     * @param arr - array with preinitialized values
     * @param threadNo - total number of threads to create and process with
     * @return true if array contains at least one non-prime number, otherwise - false
     */
    public static boolean PrimeArray(ArrayList<Long> arr, int threadNo) {

        CountDownLatch latch = new CountDownLatch(threadNo);
        ExecutorService executor = Executors.newFixedThreadPool(threadNo);
        WorkerThread[] workers = new WorkerThread[threadNo];

        int len = arr.size();
        int gap = len / threadNo;
        int s = 0;
        int e = 0;

        for(int i = 0; i != threadNo; i++) {

            if(s + gap < len)
                e += gap;
            else
                e = len - 1;

            workers[i] = new WorkerThread(s, e, arr, latch);
            s = e;
        }

        for (Runnable worker: workers) {
            executor.execute(worker);
        }

        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException ex) {
            executor.shutdownNow();
        }

                for(int i = 0; i!=workers.length; i++)
            if(workers[i].ShowAnswer())
                return true;

        return false;
    }
}
