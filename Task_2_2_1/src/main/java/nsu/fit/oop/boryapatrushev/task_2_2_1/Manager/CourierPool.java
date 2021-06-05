package nsu.fit.oop.boryapatrushev.task_2_2_1.Manager;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Logger;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Courier;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for courier management
 */
public class CourierPool {

    private List<Courier> couriers;
    private ExecutorService executor;

    /**
     * Init method
     * Creates thread pool of couriers
     * Starts execution
     * @param incomingOrders pre-initialized incoming orders queue
     * @param warehouse pre-initialized warehouse
     * @param logger pre-initialized logger
     * @param workers list of containing info about couriers
     */
    public void init(IncomingOrders incomingOrders,
                     Warehouse warehouse,
                     Logger logger,
                     List<Courier> workers,
                     CountDownLatch latch) {

        couriers = workers;
        executor = Executors.newFixedThreadPool(couriers.size());
        ReentrantLock locker = new ReentrantLock(true);

        for (Courier courier: couriers) {
            courier.setLogger(logger);
            courier.setWarehouse(warehouse);
            courier.setLocker(locker);
            courier.setLatch(latch);
            executor.execute(courier);
        }
    }

    /**
     * Method for stopping pool and all couriers
     */
    public void stop() {
        for (Courier courier: couriers) {
            courier.stop();
        }
        executor.shutdown();
    }
}
