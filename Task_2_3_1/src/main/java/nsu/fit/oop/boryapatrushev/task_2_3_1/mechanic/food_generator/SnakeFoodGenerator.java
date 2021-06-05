package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.food_generator;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simple food generator based on {@link FoodGenerator} specification
 * Chooses random point and checks if it's occupied
 * Generates food until {@code maxFoodCnt} satisfied or generate one food object
 * (it's just a point, more precisely) if {@code one} flag is set on {@code true}
 */
public class SnakeFoodGenerator extends FoodGenerator {

    /**
     * Inherited from {@link FoodGenerator}
     */
    public SnakeFoodGenerator(ArrayList<Point> pointsToAvoid, int maxFoodCnt, int width, int height) {
        super(pointsToAvoid, maxFoodCnt, width, height);
    }

    /**
     * Implementation of {@code generate} method
     * @param food array with food info
     * @param one flag if one amount of food is needed
     */
    @Override
    public void generate(ArrayList<Point> food, boolean one) {

        if (!one) {
            food.clear();
        }
        Random random = new Random();
        int j;

        j = one ? 1 : maxFoodCnt;

        for (int i = 0; i < j; i++) {
            Point point = new Point(random.nextInt(width), random.nextInt(height));

            while (pointsToAvoid.contains(point) || ((point.getX() == width / 2) && point.getY() == height / 2)) {
                point.setX(random.nextInt(width));
                point.setY(random.nextInt(height));
            }
            food.add(point);
        }
    }
}
