import nsu.fit.oop.boryapatrushev.task_2_2_1.Manager.OrderManager;

import org.junit.Assert;
import org.junit.Test;

public class OrderManagerTest {
    private static final int NUMBER_OF_ORDERS = 3;
    @Test
    public void test1() {

        OrderManager orderManager = new OrderManager();
        orderManager.init("bakers_test.json", "couriers_test.json", "config_test.json");

        for (int i = 0; i != NUMBER_OF_ORDERS; i++) {
            orderManager.addOrder();
        }

        orderManager.stop();
        Assert.assertEquals(orderManager.getTotalOrders(), NUMBER_OF_ORDERS);
    }
}
