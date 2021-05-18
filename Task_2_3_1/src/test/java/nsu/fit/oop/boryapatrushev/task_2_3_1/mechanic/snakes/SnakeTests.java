package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes;

import org.junit.Assert;
import org.junit.Test;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

public class SnakeTests {
    /**
     * Check actual length
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test1() {

        Snake snake = new Snake(new Point(10, 10));
        snake.setUp();
        for (int i = 0; i < 5; i++) {
            snake.extend();
        }

        Assert.assertEquals(snake.length, 6);
    }

    /**
     * Check self-intersection
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test2() {

        Snake snake = new Snake(new Point(0, 0));
        snake.setUp();

        for (int i = 0; i < 8; i++) {
            snake.extend();
            snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
        }

        snake.setRight();
        moveTwoForward(snake);

        snake.setDown();
        moveTwoForward(snake);

        snake.setLeft();
        moveTwoForward(snake);

        Assert.assertFalse(snake.isSafe());
    }

    private void moveTwoForward(Snake snake) {
        snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
        snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
    }
}
