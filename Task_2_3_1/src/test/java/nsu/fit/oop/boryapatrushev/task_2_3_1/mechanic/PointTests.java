package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic;

import org.junit.Assert;
import org.junit.Test;

public class PointTests {

    /**
     * Check translate method
     */
    @Test
    public void test_1() {

        Point point = new Point(25, 7);
        Point testPoint = point.translate(10, 23);

        Assert.assertEquals(testPoint, new Point(35, 30));
    }

    /**
     * Check equals method
     */
    @Test
    public void test_2() {

        Point point_1 = new Point(1,2);
        Point point_2 = new Point(2,1);

        Assert.assertNotEquals(point_1, point_2);
    }
    /**
     * Check equals method
     */
    @Test
    public void test_3() {
        Assert.assertEquals(2, 2);
    }
}
