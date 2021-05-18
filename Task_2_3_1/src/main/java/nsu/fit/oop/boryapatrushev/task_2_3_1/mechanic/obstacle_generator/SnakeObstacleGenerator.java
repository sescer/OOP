package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.obstacle_generator;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simple obstacle generator based on {@link ObstacleGenerator} specification
 * Chooses random point and checks if it's occupied according to current wall length
 * and previous point position.
 * Generates obstacles
 * until {@code maxWallCnt} satisfied.
 */
public class SnakeObstacleGenerator extends ObstacleGenerator {

    private static final int ROT_SIDES = 4;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    /**
     * Inherited from {@link ObstacleGenerator}
     */
    public SnakeObstacleGenerator(int maxWallLength, int maxWallCnt, int width, int height) {
        super(maxWallLength, maxWallCnt, width, height);
    }

    /**
     * Implementation of {@code generate} method
     * @param obstacles array with obstacles
     */
    @Override
    public void generate(ArrayList<Point> obstacles) {

        obstacles.clear();
        Random rnd = new Random();
        Point point = null;

        int rotation;
        boolean first;
        int trs;

        for (int i = 0; i < maxWallCnt; i++) {

            first = true;

            for (int j = 0; j < maxWallLength; j++) {

                trs = 0;

                do {
                    if (first) {
                        point = new Point(rnd.nextInt(width), rnd.nextInt(height));
                    } else {
                        Point prevPoint = obstacles.get(obstacles.size() - 1);
                        rotation = rnd.nextInt(ROT_SIDES);
                        switch (rotation) {
                            default:
                                break;
                            case RIGHT:
                                point = new Point(prevPoint.getX() + 1, prevPoint.getY());
                                break;
                            case LEFT:
                                point = new Point(prevPoint.getX() - 1, prevPoint.getY());
                                break;
                            case UP:
                                point = new Point(prevPoint.getX(), prevPoint.getY() - 1);
                                break;
                            case DOWN:
                                point = new Point(prevPoint.getX(), prevPoint.getY() + 1);
                                break;
                        }
                        trs++;
                    }

                    if (trs == ROT_SIDES) {
                        break;
                    }

                } while (obstacles.contains(point) || ((point.getX() == width / 2) && point.getY() == height / 2));

                if (j == 0) {
                    first = false;
                }
                if (trs == ROT_SIDES) {
                    break;
                }

                obstacles.add(point);
            }
        }
    }
}
