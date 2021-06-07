package nsu.fit.oop.boryapatrushev.task_2_2_1.Manager;

import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Logger;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.OrderStatus;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Utils.JSONReader;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Baker;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Courier;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Class for adding and storing the status of all incoming orders,
 * employee management and shutdown services.
 */
public class OrderManager {

    private int totalOrders;
    private final ArrayList<Order> journal = new ArrayList<>();
    private Logger logger;
    private int orderId = 1;

    private CountDownLatch courierLatch;
    private CountDownLatch bakerLatch;

    private CourierPool courierPool;
    private BakerPool bakerPool;

    private IncomingOrders incomingOrders;

    private boolean toFile = false;
    private boolean additionalLogs = false;


    /**
     * Initialization method
     * Creates worker pools, queues, warehouses
     * Gets config info from JSON files
     * @param bakerConfig - JSON file of bakers config
     * @param courierConfig - JSON file of courier config
     * @param warehouseConfig - JSON file of warehouse config
     */
    public void init(String bakerConfig,
                     String courierConfig,
                     String warehouseConfig) {

        Warehouse warehouse = JSONReader.readJsonWarehouseConfig(warehouseConfig);
        incomingOrders = new IncomingOrders();

        System.out.println("Initializing workers...");

        courierPool = new CourierPool();
        bakerPool = new BakerPool();

        List<Baker> bakers = JSONReader.readJsonBakerList(bakerConfig);
        List<Courier> couriers = JSONReader.readJsonCourierList(courierConfig);

        if (bakers != null) {
            this.bakerLatch = new CountDownLatch(bakers.size());
        }
        if (couriers != null) {
            this.courierLatch = new CountDownLatch(couriers.size());
        }

        this.logger = new Logger(warehouse, incomingOrders, additionalLogs);

        courierPool.init(incomingOrders, warehouse, logger, couriers, courierLatch);
        bakerPool.init(incomingOrders, warehouse, logger, bakers, bakerLatch);

        logger.customLog("Service ready");
    }

    /**
     * Method for adding orders to incoming queue
     */
    public synchronized void addOrder() {

        Order newOrder = new Order(orderId++);
        logger.log(null, newOrder);
        incomingOrders.addOrder(newOrder);

        newOrder.setAcceptedTime(LocalDateTime.now());
        journal.add(newOrder);

        totalOrders++;
    }

    /**
     * Method for stopping workers and closing service
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public void stop() {

        logger.customLog("Baking and delivering remaining orders...");

        while (!checkIfDelivered()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bakerPool.stop();
        courierPool.stop();

        try {
            bakerLatch.await();
            courierLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (toFile) {
                logger.logStats(journal, "logs");
            } else {
                logger.logStats(journal, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.customLog("Pizzeria simulation finished");
    }

    /**
     * Set additional logs flag
     * @param logs boolean flag (true - activated)
     */
    public void setAdditionalLogs(boolean logs) {
        this.additionalLogs = logs;
    }

    /**
     * Set path to file for writing logs
     * @param path for writing logs
     */
    public void setPath(boolean path) {
        this.toFile = path;
    }

    /**
     * Check If order is delivered.
     * @return true - if order is delivered, false - otherwise.
     */
    public boolean checkIfDelivered() {
        for (Order order: journal) {
            if (!order.getStatus().equals(OrderStatus.delivered)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get total orders in session.
     * @return total orders.
     */
    public int getTotalOrders() {
        return totalOrders;
    }
}

