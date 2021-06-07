package nsu.fit.oop.boryapatrushev.task_2_2_1.Storage;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Class implementing incoming orders queue behaviour
 */
public class IncomingOrders {

    private final LinkedBlockingQueue<Order> queue;

    public IncomingOrders() {
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Add order to the queue
     * @param order order to add
     */
    public void addOrder(Order order) {
        try {
            queue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Take and remove order from the queue
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
     * Get current number of orders in queue
     * @return int - current number of orders
     */
    public int getCurrentSize() {
        return this.queue.size();
    }
}
