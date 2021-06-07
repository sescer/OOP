package nsu.fit.oop.boryapatrushev.task_2_2_1.Manager;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Logger;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Baker;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for bakers management
 */
public class BakerPool {

    private List<Baker> bakers;
    private ExecutorService executor;

    /**
     * Init method
     * Creates thread pool of bakers
     * Starts execution
     * @param incomingOrders pre-initialized incoming orders queue
     * @param warehouse pre-initialized warehouse
     * @param logger pre-initialized logger
     * @param workers list of containing info about bakers
     */
    public void init(IncomingOrders incomingOrders,
                     Warehouse warehouse,
                     Logger logger,
                     List<Baker> workers,
                     CountDownLatch latch) {

        bakers = workers;
        executor = Executors.newFixedThreadPool(bakers.size());
        ReentrantLock locker = new ReentrantLock(true);

        for (Baker baker: bakers) {
            baker.setLogger(logger);
            baker.setIncomingOrders(incomingOrders);
            baker.setWarehouse(warehouse);
            baker.setLocker(locker);
            baker.setLatch(latch);
            executor.execute(baker);
        }
    }

    /**
     * Method for stopping pool and all bakers
     */
    public void stop() {
        for (Baker baker: bakers) {
            baker.stop();
        }
        executor.shutdown();
    }
}
