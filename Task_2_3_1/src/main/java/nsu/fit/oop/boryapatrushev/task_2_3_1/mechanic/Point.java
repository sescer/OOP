package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic;

public final class Point {
    private static final int PRIME_NUM = 31;
    private int x;
    private int y;

    /**
     * Constructor
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Y coordinate
     */
    public int getY() {
        return y;
    }

    public void setX(int xCord) {
        this.x = xCord;
    }

    public void setY(int yCord) {
        this.y = yCord;
    }

    /**
     * @param dx The change in x.
     * @param dy The change in y.
     * @return A new Point which is the result of translation of this point.
     */
    public Point translate(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    /**
     * @param other The "other" point to compare against.
     * @return if the other Object is an instance of Point and
     * has the same coordinates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        }
        Point point = (Point) other;
        return x == point.x & y == point.y;
    }

    /**
     * Override hashCode() with equals
     * Two points that are equal - return the same hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = x;
        result = PRIME_NUM * result + y;
        return result;
    }

    public String toString() {
        return x + ", " + y;
    }
}
