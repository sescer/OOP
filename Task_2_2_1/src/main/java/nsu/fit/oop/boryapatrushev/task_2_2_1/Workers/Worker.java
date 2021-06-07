package nsu.fit.oop.boryapatrushev.task_2_2_1.Workers;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Logger;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An abstract worker class containing all the required methods
 * and fields for the further implementation of inherited classes.
 *
 * It can be used to further expand the functionality of the service
 */
public abstract class Worker implements Runnable {

    int id;
    String name;
    int timeConsumption;
    IncomingOrders incomingOrders;
    Warehouse warehouse;
    Logger logger;
    boolean isRunning;
    ReentrantLock locker;
    CountDownLatch latch;

    public abstract void run();

    public abstract String info();

    /**
     * Provide access to the warehouse
     * @param warehouse pre-initialized warehouse
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Provide access to the incoming queue
     * @param incomingOrders pre-initialized incomingOrders queue
     */
    public void setIncomingOrders(IncomingOrders incomingOrders) {
        this.incomingOrders = incomingOrders;
    }

    /**
     * Provide access to the log system
     * @param logger pre-initialized logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Provide access to the lock from the warehouse/incomingQueue
     * @param locker pre-initialized ReentrantLock
     */
    public void setLocker(ReentrantLock locker) {
        this.locker = locker;
    }

    /**
     * Provide access to the global counter of active workers
     * @param latch pre-initialized CountDownLatch
     */
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * Stops current worker
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Override method toString
     * @return string of worker's name
     */
    @Override
    public String toString() {
        return "\"" + this.name + "\"";
    }
}
