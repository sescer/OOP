package nsu.fit.oop.boryapatrushev.task_2_2_1.Order;

/**
 * Possible statuses of orders
 */
public enum OrderStatus {
    inQueue,
    gettingReady,
    ready,
    movedToWarehouse,
    isTaken,
    inDelivery,
    delivered {
        @Override
        public OrderStatus next() {
            return delivered;
        }
    };

    /**
     * Get next status value
     * @return status value
     */
    public OrderStatus next() {
        return values()[ordinal() + 1];
    }
}
