package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.obstacle_generator;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.ArrayList;

/**
 * Template class for custom obstacle generators
 * Provides all methods for interaction with game engine
 */
public abstract class ObstacleGenerator {

    int maxWallLength;
    int maxWallCnt;
    int width;
    int height;

    /**
     * Constructor
     * @param maxWallLength maximum wall length
     * @param maxWallCnt maximum wall number
     * @param width playground width
     * @param height playground height
     */
    ObstacleGenerator(int maxWallLength, int maxWallCnt, int width, int height) {
        this.maxWallCnt = maxWallCnt;
        this.maxWallLength = maxWallLength;
        this.width = width;
        this.height = height;
    }

    /**
     * The method in which obstacle generation should happen,
     * accepts obstacles in obstacle array should be cleared and replaced with new values
     * inside this method.
     * @param obstacles array with obstacles
     */
    public abstract void generate(ArrayList<Point> obstacles);
}
