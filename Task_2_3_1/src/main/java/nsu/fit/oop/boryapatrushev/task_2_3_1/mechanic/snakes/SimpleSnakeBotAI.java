package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.Random;

/**
 * Simple snake bot AI based on {@link SnakeBotAI} specification.
 * Checks if point in front of the controlled snake contains an obstacle,
 * If contains, changes rotation based on current one.
 * Also, change direction randomly every {@code action = 10} frames.
 */
public class SimpleSnakeBotAI extends SnakeBotAI {

    private final Random rnd;
    private final int action = 10;
    private int curAction = action;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int ROT_SIDES = 4;

    /**
     * Inherited from {@link SnakeBotAI}
     * Added creation of random
     */
    public SimpleSnakeBotAI(Snake snake, Playfield playfield) {
        super(snake, playfield);
        rnd = new Random();
    }

    /**
     * Implementation of {@code update} method
     */
    @Override
    public void update() {

        checkEnv();

        if (curAction == action) {

            curAction = 0;
            int rot = rnd.nextInt(ROT_SIDES);

            switch (rot) {
                default:
                    break;
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
            }
        }

        curAction++;
    }

    /**
     * Helper method to check one point forward and change rotation
     * if it contains an obstacle
     */
    private void checkEnv() {

        Point env = snake.getHead().translate(snake.xVelocity, snake.yVelocity);

        if (checkObstacle(env)) {

            curAction = 0;
            int rot = rnd.nextInt(2);

            // up or down
            if (snake.yVelocity == -1 || snake.yVelocity == 1) {
                switch (rot) {
                    default:
                        break;
                    case 0:
                        snake.setLeft();
                        break;
                    case 1:
                        snake.setRight();
                        break;
                }
            } else if (snake.xVelocity == 1 || snake.xVelocity == -1) {
                switch (rot) {
                    default:
                        break;
                    case 0:
                        snake.setUp();
                        break;
                    case 1:
                        snake.setDown();
                        break;
                }
            }
        }
    }

    /**
     * Check if point occupied by obstacles
     * @param point point to check
     * @return true if occupied, otherwise - nope
     */
    private boolean checkObstacle(Point point) {
        return playfield.getObstacles().contains(point);
    }
}
