
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Baker;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Logger;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.IncomingOrders;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Storage.Warehouse;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Order.Order;
import nsu.fit.oop.boryapatrushev.task_2_2_1.Workers.Courier;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("checkstyle:MagicNumber")
public class WorkersTests {

    private final IncomingOrders incomingOrders = new IncomingOrders();
    private final Warehouse warehouse = new Warehouse(3);
    private final ReentrantLock locker = new ReentrantLock();
    private final Logger logger = new Logger(warehouse, incomingOrders, true);

    @Test
    public void test1() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        Baker baker = new Baker(1, "Tester", 10, false);
        baker.setIncomingOrders(incomingOrders);
        baker.setLatch(latch);
        baker.setLogger(logger);
        baker.setWarehouse(warehouse);
        baker.setLocker(locker);

        incomingOrders.addOrder(new Order(1));
        incomingOrders.addOrder(new Order(2));
        incomingOrders.addOrder(new Order(3));

        Thread thread = new Thread(baker);
        thread.start();

        thread.join(5000);

        baker.stop();

        if (warehouse.getSize() != 3) {
            Assert.fail();
        }
    }

    @Test
    public void test2() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Warehouse warehouse1 = new Warehouse(3);

        Courier courier = new Courier(1, "Tester", 2, 10, 10, false);
        courier.setIncomingOrders(incomingOrders);
        courier.setLatch(latch);
        courier.setLogger(logger);
        courier.setWarehouse(warehouse1);
        courier.setLocker(locker);

        warehouse1.addOrder(new Order(1));
        warehouse1.addOrder(new Order(2));
        warehouse1.addOrder(new Order(3));

        Thread thread = new Thread(courier);
        thread.start();

        thread.join(5000);

        courier.stop();

        if (warehouse1.getSize() != 0) {
            Assert.fail();
        }
    }
}
