package nsu.fit.oop.boryapatrushev.task_2_2_1.Order;

import java.time.LocalDateTime;

/**
 * Class implementing order object
 */
public class Order {

    private final int orderId;
    private String bakerName;
    private String courierName;
    LocalDateTime acceptedTime;
    LocalDateTime deliveredTime;
    private OrderStatus status = OrderStatus.inQueue;

    public Order(int id) {
        this.orderId = id;
    }

    /**
     * Get order id
     * @return int - id
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * Get current status of order
     * @return status - current status
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
     * Get baker name
     * @return String - name
     */
    public String getBakerName() {
        return this.bakerName;
    }

    /**
     * Get courier name
     * @return String - name
     */
    public String getCourierName() {
        return this.courierName;
    }

    /**
     * Change current status of the order
     */
    public void changeStatus() {
        this.status = this.status.next();
    }

    /**
     * Set baker name
     * @param name string name to set
     */
    public void setBakerName(String name) {
        this.bakerName = name;
    }

    /**
     * Set courier name
     * @param name string name to set
     */
    public void setCourierName(String name) {
        this.courierName = name;
    }

    /**
     * Set time when order was accepted
     * @param time LocalDateTime adoption time
     */
    public void setAcceptedTime(LocalDateTime time) {
        this.acceptedTime = time;
    }

    /**
     * Set time when order was delivered
     * @param time LocalDateTime delivery time
     */
    public void setDeliveredTime(LocalDateTime time) {
        this.deliveredTime = time;
    }

    /**
     * Override method toString.
     * @return string of Order ID
     */
    @Override
    public String toString() {
        return "Order #" + this.orderId;
    }
}
