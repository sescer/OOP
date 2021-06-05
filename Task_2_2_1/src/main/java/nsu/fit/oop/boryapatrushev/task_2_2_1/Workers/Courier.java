package nsu.fit.oop.boryapatrushev.task_2_2_1.Workers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Inherited class from {@link Worker} implementing courier behaviour
 * Additionally has a trunk and time to wait for the next order
 */
public final class Courier extends Worker {

    private final int trunkCapacity;
    private final int waitForOrder;
    private final ArrayList<Order> trunk;
    private boolean logs = true;

    @JsonCreator
    public Courier(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("trunkCapacity") int trunkCapacity,
                   @JsonProperty("timeConsumption") int timeConsumption,
                   @JsonProperty("waitForOrder") int waitForOrder) {

        this.id = id;
        this.name = name;
        this.trunkCapacity = trunkCapacity;
        this.timeConsumption = timeConsumption;
        this.waitForOrder = waitForOrder;
        this.trunk = new ArrayList<>();
        this.isRunning = true;
    }

    public Courier(int id,
                   String name,
                   int trunkCapacity,
                   int timeConsumption,
                   int waitForOrder,
                   boolean logs) {

        this(id, name, trunkCapacity, timeConsumption, waitForOrder);
        this.logs = false;
    }

    public String info() {
        return "[current size of trunk: " + this.trunk.size() + " of " + this.trunkCapacity + "]";
    }

    /**
     * Run method for concurrency
     */
    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public void run() {

        while (isRunning) {

            locker.lock();

            try {
                for (int i = 0; i != trunkCapacity; i++) {

                    Order curOrder;

                    if (trunk.size() != 0) {
                        curOrder = warehouse.takeOrder(this.waitForOrder);
                    } else {
                        curOrder = warehouse.takeOrder();
                    }

                    if (curOrder != null) {
                        curOrder.setCourierName(this.name);
                        trunk.add(curOrder);
                    }
                    Thread.sleep(1000); // time to add order in the trunk

                    if (curOrder != null) {
                        if (logs) {
                            logger.log(this, curOrder);
                        }
                    } else {
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }

            try {
                if (logs) {
                    for (Order order: trunk) {
                        logger.log(this, order);
                    }
                }

                for (int i = 0; i != trunk.size(); i++) {
                    Thread.sleep(this.timeConsumption);
                }

                for (Order order: trunk) {
                    order.setDeliveredTime(LocalDateTime.now());
                    if (logs) {
                        logger.log(this, order);
                    }
                }

                trunk.clear();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        if (logs) {
            logger.exitLog(this);
        }
    }
}
