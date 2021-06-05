package nsu.fit.oop.boryapatrushev.task_2_2_1.Utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class ArrayBlockingQueue<E> {

    /** Main lock guarding all access */
    final ReentrantLock lock;

    /** Condition for waiting takes */
    private final Condition notEmpty;

    /** Condition for waiting puts */
    private final Condition notFull;

    final Object[] items;

    /** items index for next take, poll, peek or remove */
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;

    /** Number of elements in the queue */
    int count;

    public ArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }

    /**
     * Creates an ArrayBlockingQueue with the given (fixed)
     * capacity and the specified access policy.
     *
     * @param capacity the capacity of this queue
     * @param fair if {@code true} then queue accesses for threads blocked
     *        on insertion or removal, are processed in FIFO order;
     *        if {@code false} the access order is unspecified.
     * @throws IllegalArgumentException if {@code capacity < 1}
     */
    public ArrayBlockingQueue(int capacity,
                              boolean fair) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }

    private static void checkNotNull(Object v) {
        if (v == null) {
            throw new NullPointerException();
        }
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);

        lock.lockInterruptibly();

        try {
            while (count == items.length) {
                notFull.await();
            }
            insert(e);
        } finally {
            lock.unlock();
        }
    }

    public E poll(long timeout,
                  TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);

        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return extract();
        } finally {
            lock.unlock();
        }
    }

    public E poll() {

        lock.lock();
        try {
            return (count == 0) ? null : extract();
        } finally {
            lock.unlock();
        }
    }

    private E take() throws InterruptedException {

        lock.lockInterruptibly();

        try {
            while (count == 0) {
                notEmpty.await();
            }
            return extract();
        } finally {
            lock.unlock();
        }
    }

    private void insert(E x) {
        items[putIndex] = x;
        putIndex = inc(putIndex);
        ++count;
        notEmpty.signal();
    }

     int inc(int i) {
        return (++i == items.length) ? 0 : i;
    }

    /**
     * Extracts element at current take position, advances, and signals.
     * Call only when holding lock.
     */
    private E extract() {
        final Object[] items = this.items;
        E x = this.<E>cast(items[takeIndex]);
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        --count;
        notFull.signal();
        return x;
    }

    @SuppressWarnings("unchecked")
    static <E> E cast(Object item) {
        return (E) item;
    }


    /**
     * Returns the number of elements in this queue.
     * @return the number of elements in this queue
     */
    public int size() {

        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
