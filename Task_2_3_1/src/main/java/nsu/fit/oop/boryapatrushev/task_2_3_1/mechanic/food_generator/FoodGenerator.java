package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.food_generator;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.ArrayList;

/**
 * Template class for custom food generators
 * Provides all methods for interaction with game engine
 */
public abstract class FoodGenerator {

    int maxFoodCnt;
    ArrayList<Point> pointsToAvoid;
    int width;
    int height;

    /**
     * Constructor
     * @param pointsToAvoid array with occupied points
     * @param maxFoodCnt maximum food number
     * @param width playground width
     * @param height playground height
     */
    FoodGenerator(ArrayList<Point> pointsToAvoid, int maxFoodCnt, int width, int height) {
        this.maxFoodCnt = maxFoodCnt;
        this.pointsToAvoid = pointsToAvoid;
        this.width = width;
        this.height = height;
    }

    /**
     * The method in which food generation should happen,
     * accepts array with food (could be empty) and boolean flag if
     * one amount of food is needed.
     * Previous food in food array should be cleared and replaced with new values
     * inside this method.
     * @param food array with food info
     * @param one flag if one amount of food is needed
     */
    public abstract void generate(ArrayList<Point> food, boolean one);
}
