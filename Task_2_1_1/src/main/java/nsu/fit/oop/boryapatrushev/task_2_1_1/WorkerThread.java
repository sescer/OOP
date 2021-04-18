package nsu.fit.oop.boryapatrushev.task_2_1_1;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Runnable class for one thread
 * Receives start and end indices for a gap to process in array
 */
public class WorkerThread implements Runnable {
    private final int start;
    private final int end;
    private final ArrayList<Long> arr;
    private final CountDownLatch latch;
    private boolean answer;

    public WorkerThread(final int start, final int end,
                        final ArrayList<Long> arr,
                        final CountDownLatch latch) {

        this.start = start;
        this.end = end;
        this.arr = arr;
        this.latch = latch;
    }

    /**
     * @return answer
     */
    public boolean showAnswer() {
        return this.answer;
    }

    /**
     * Override method run
     */
    @Override
    public void run() {
        System.out.println("Thread started "
                + Thread.currentThread().getName());
        answer = check();
        latch.countDown();
        System.out.println("Thread finished "
                + Thread.currentThread().getName());
    }

    private boolean check() {

        for (int i = start; i <= end; i++) {
            if (!IsPrime.prime(arr.get(i))) {
                return true;
            }
        }
        return false;
    }
}
