package nsu.fit.oop.boryapatrushev.task_2_2_1.Storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Utils.ArrayBlockingQueue;

import java.util.concurrent.TimeUnit;

/**
 * Class implementing warehouse behaviour
 */
public class Warehouse {

    private final ArrayBlockingQueue<Order> queue;
    private final int capacity;
    private int totalCnt = 0;

    public Warehouse(@JsonProperty("capacity") int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity, true);
        this.capacity = capacity;
    }

    /**
     * Get current number of orders in warehouse
     * @return int - number of orders
     */
    public int getSize() {
        return this.queue.size();
    }

    /**
     * Get possible capacity of warehouse
     * @return int - possible capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Add order to the warehouse
     * @param order order to add
     */
    public void addOrder(Order order) {
        try {
            totalCnt++;
            queue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Take and delete order from the queue
     * @return order
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public Order takeOrder() {
        try {
            return queue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Take and delete order from the queue for a given period of time
     * @param waitTime milliseconds to wait
     * @return order if it is existing in queue, null - otherwise
     */
    public Order takeOrder(int waitTime) {
        try {
            return queue.poll(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get counter showing how many orders were here during the work
     * @return int - counter
     */
    public int getTotalCnt() {
        return this.totalCnt;
    }

}
