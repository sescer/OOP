package nsu.fit.oop.boryapatrushev.task_2_2_1.Workers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;

/**
 * Inherited class from {@link Worker} implementing baker behaviour
 */
public final class Baker extends Worker {

    boolean flag = false;
    boolean logs = true;

    @JsonCreator
    public Baker(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("timeConsumption") int timeConsumption) {

        this.id = id;
        this.name = name;
        this.timeConsumption = timeConsumption;
        this.isRunning = true;
    }

    public Baker(int id,
                 String name,
                 int timeConsumption,
                 boolean logs) {

        this(id, name, timeConsumption);
        this.logs = false;
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public void run() {

        while (isRunning) {

            locker.lock();

            Order currentOrder;

            do {
                currentOrder = incomingOrders.takeOrder();

                try {
                    if (currentOrder != null) {

                        locker.unlock();
                        flag = true;
                        currentOrder.setBakerName(this.name);
                        if (logs) {
                            logger.log(this, currentOrder);
                        }
                        Thread.sleep(this.timeConsumption);
                        if (logs) {
                            logger.log(this, currentOrder);
                        }
                        Thread.sleep(1000);
                        warehouse.addOrder(currentOrder);
                        if (logs) {
                            logger.log(this, currentOrder);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (currentOrder == null && isRunning);

            if (!flag) {
                locker.unlock();
            }
            flag = false;

        }

        latch.countDown();
        logger.exitLog(this);
    }

    public String info() {
        return null;
    }
}
